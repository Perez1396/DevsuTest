package com.devsu.bank.service.impl;

import com.devsu.bank.dto.*;
import com.devsu.bank.enums.MovementType;
import com.devsu.bank.exception.BankException;
import com.devsu.bank.model.Movements;
import com.devsu.bank.repository.MovementRepository;
import com.devsu.bank.service.IAccountService;
import com.devsu.bank.service.IMovementService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.devsu.bank.utils.MovementConstants.*;

@Slf4j
@Service
public class MovementServiceImpl implements IMovementService {
    private final static Integer MAX_AMOUNT_PER_DAY = 1000;

    @Autowired
    MovementRepository movementRepository;

    @Autowired
    IAccountService accountService;

    @Override
    public MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO) throws BankException {
        log.info("Datos de movimiento a crear: {}", movementRequestDTO);
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(movementRequestDTO.getAccountId());
        return accountResponseDTO == null ? null : addMovementToAccount(accountResponseDTO, movementRequestDTO);
    }

    public List<MovementResponseDTO> getAllMovementsByAccountId(Integer accountId) {
        log.info("Id de cuenta para consultar todos los movimientos: {}", accountId);
        ModelMapper modelMapper = new ModelMapper();
        return movementRepository.findAllByAccountId(accountId)
                .stream()
                .map(movements -> modelMapper.map(movements, MovementResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovementResponseDTO> getAllMovements() {
        ModelMapper modelMapper = new ModelMapper();
        List<Movements> movementsList = movementRepository.findAll();
        return movementsList.stream()
                .map(movements -> modelMapper.map(movements, MovementResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovementResponseDTO getMovementById(Integer movementId) {
        log.info("Id de movimiento a consultar: {}", movementId);
        ModelMapper modelMapper = new ModelMapper();
        return movementRepository.findById(movementId)
                .map(movement -> modelMapper.map(movement, MovementResponseDTO.class))
                .orElse(null);
    }

    @Override
    public void deleteMovementById(Integer movementId) {
        log.info("Id de movimiento a ser eliminado: {}", movementId);
        MovementResponseDTO movementResponseDTO = getMovementById(movementId);
        if (movementResponseDTO != null) {
            movementRepository.deleteById(movementId);
        }
    }

    private MovementResponseDTO addMovementToAccount(AccountResponseDTO accountResponseDTO, MovementRequestDTO movementRequestDTO) throws BankException {
        log.info("Datos de cuenta para añadir los movimientos: {}, datos de movimiento a añadir: {}", accountResponseDTO, movementRequestDTO);
        ModelMapper modelMapper = new ModelMapper();
        calculateNewBalance(accountResponseDTO, movementRequestDTO);
        Movements movements = modelMapper.map(movementRequestDTO, Movements.class);
        movements.setDate(LocalDate.now());
        movements.setId(null);
        accountService.patchBalanceAccount(accountResponseDTO.getId(), modelMapper.map(accountResponseDTO, AccountRequestDTO.class));
        return modelMapper.map(movementRepository.save(movements), MovementResponseDTO.class);
    }

    private void calculateNewBalance(AccountResponseDTO accountResponseDTO, MovementRequestDTO movementRequestDTO) throws BankException {
        log.info("Datos de cuenta para añadir los movimientos: {}, datos de movimiento a añadir: {}", accountResponseDTO, movementRequestDTO);
        int newBalance = 0;
        balanceValidator(accountResponseDTO, movementRequestDTO);

        Integer amountOfPreviousMovements = getMaxAmountPerDay(accountResponseDTO.getMovementsList());
        int newMaxAmount = amountOfPreviousMovements + movementRequestDTO.getValue();
        if (movementRequestDTO.getMovementType().equals(MovementType.DEBITO.toString())) {
            if (newMaxAmount <= MAX_AMOUNT_PER_DAY) {
                newBalance = accountResponseDTO.getInitialBalance() - movementRequestDTO.getValue();
            } else {
                log.error(NO_BALANCE_ERROR_LOG, movementRequestDTO.getValue());
                throw new BankException(MAX_AMOUNT_REACHED, HttpStatus.BAD_REQUEST.value());
            }
        }
        if (movementRequestDTO.getMovementType().equals(MovementType.CREDITO.toString())) {
            newBalance = accountResponseDTO.getInitialBalance() + movementRequestDTO.getValue();
        }
        movementRequestDTO.setBalance(newBalance);
        accountResponseDTO.setInitialBalance(newBalance);
    }

    private void balanceValidator(AccountResponseDTO accountResponseDTO, MovementRequestDTO movementRequestDTO) throws BankException {
        if (accountResponseDTO.getInitialBalance() == 0 && movementRequestDTO.getMovementType().equals(MovementType.DEBITO.toString())) {
            log.error(NO_BALANCE_ERROR_LOG, movementRequestDTO.getValue());
            throw new BankException(NO_BALANCE, HttpStatus.BAD_REQUEST.value());
        }
        if (movementRequestDTO.getMovementType().equals(MovementType.DEBITO.toString()) && movementRequestDTO.getValue() > accountResponseDTO.getInitialBalance()) {
            log.error(WITHDRAWAL_ERROR_LOG, movementRequestDTO.getValue(), accountResponseDTO.getInitialBalance());
            throw new BankException(WITHDRAWAL_EXCEDEED, HttpStatus.BAD_REQUEST.value());
        }
    }

    private Integer getMaxAmountPerDay(List<MovementResponseDTO> movementResponseDTOList) {
        log.info("Lista de movimientos a iterar para sumar el valor de transacciones: {}", movementResponseDTOList);
        return movementResponseDTOList
                .stream()
                .filter(movementResponseDTO -> movementResponseDTO.getDate().equals(LocalDate.now()))
                .filter(movementResponseDTO -> movementResponseDTO.getMovementType().equals(MovementType.DEBITO.toString()))
                .mapToInt(MovementResponseDTO::getValue)
                .sum();
    }

}
