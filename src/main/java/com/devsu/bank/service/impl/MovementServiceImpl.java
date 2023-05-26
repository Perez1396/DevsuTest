package com.devsu.bank.service.impl;

import com.devsu.bank.dto.*;
import com.devsu.bank.enums.MovementType;
import com.devsu.bank.model.Client;
import com.devsu.bank.model.Movements;
import com.devsu.bank.repository.MovementRepository;
import com.devsu.bank.service.IAccountService;
import com.devsu.bank.service.IMovementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements IMovementService {
    private final static Integer MAX_AMOUNT_PER_DAY = 1000;

    @Autowired
    MovementRepository movementRepository;

    @Autowired
    IAccountService accountService;

    @Override
    public MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO) {
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(movementRequestDTO.getAccountId());
        return accountResponseDTO == null ? null : addMovementToAccount(accountResponseDTO, movementRequestDTO);
    }

    public List<MovementResponseDTO> getAllMovementsByAccountId(Integer accountId) {
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
        ModelMapper modelMapper = new ModelMapper();
        return movementRepository.findById(movementId)
                .map(movement -> modelMapper.map(movement, MovementResponseDTO.class))
                .orElse(null);
    }

    @Override
    public void deleteMovementById(Integer movementId) {
        MovementResponseDTO movementResponseDTO = getMovementById(movementId);
        if (movementResponseDTO != null){
            movementRepository.deleteById(movementId);
        }
    }

    private MovementResponseDTO addMovementToAccount(AccountResponseDTO accountResponseDTO, MovementRequestDTO movementRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();

        calculateNewBalance(accountResponseDTO, movementRequestDTO);
        Movements movements = modelMapper.map(movementRequestDTO, Movements.class);
        movements.setDate(LocalDate.now());
        movements.setId(null);
        accountService.patchBalanceAccount(accountResponseDTO.getId(), modelMapper.map(accountResponseDTO, AccountRequestDTO.class));
        return modelMapper.map(movementRepository.save(movements), MovementResponseDTO.class);
    }

    private void calculateNewBalance(AccountResponseDTO accountResponseDTO, MovementRequestDTO movementRequestDTO) {
        int newBalance = 0;
        balanceValidator(accountResponseDTO, movementRequestDTO);

        Integer maxAmount = getMaxAmountPerDay(accountResponseDTO.getMovementsList());
        int newMaxAmount = maxAmount + movementRequestDTO.getValue();
        if (movementRequestDTO.getMovementType().equals(MovementType.DEBITO.toString())) {
            if (newMaxAmount <= MAX_AMOUNT_PER_DAY){
                newBalance = accountResponseDTO.getInitialBalance() - movementRequestDTO.getValue();
            }else{
                throw new RuntimeException(); //TODO Añadir el mensaje de cupo diario excedido
            }
        }
        if (movementRequestDTO.getMovementType().equals(MovementType.CREDITO.toString())) {
            newBalance = accountResponseDTO.getInitialBalance() + movementRequestDTO.getValue();
        }
        movementRequestDTO.setBalance(newBalance);
        accountResponseDTO.setInitialBalance(newBalance);
    }

    private void balanceValidator(AccountResponseDTO accountResponseDTO, MovementRequestDTO movementRequestDTO) {
        if (accountResponseDTO.getInitialBalance() == 0 && movementRequestDTO.getMovementType().equals(MovementType.DEBITO.toString())) {
            throw new RuntimeException(); //TODO Añadir el mensaje de saldo no disponible idea usar un exception
        }
        if (movementRequestDTO.getMovementType().equals(MovementType.DEBITO.toString()) && movementRequestDTO.getValue() > accountResponseDTO.getInitialBalance()) {
            throw new RuntimeException(); //TODO Añadir el mensaje de saldo no disponible idea usar un exception
        }
    }

    public Integer getMaxAmountPerDay(List<MovementResponseDTO> movementResponseDTOList) {
        return movementResponseDTOList
                .stream()
                .filter(movementResponseDTO -> movementResponseDTO.getDate().equals(LocalDate.now()))
                .filter(movementResponseDTO -> movementResponseDTO.getMovementType().equals("debito"))
                .mapToInt(MovementResponseDTO::getValue)
                .sum();
    }

}
