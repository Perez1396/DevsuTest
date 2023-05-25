package com.devsu.bank.service.impl;

import com.devsu.bank.dto.*;
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
    private static Integer MAX_AMOUNT_PER_DAY = 0;

    @Autowired
    MovementRepository movementRepository;

    @Autowired
    IAccountService accountService;

    @Override
    public MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO) {
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(movementRequestDTO.getAccountId());
        return accountResponseDTO == null ? null : addMovementToAccount(accountResponseDTO, movementRequestDTO);
    }

    @Override
    public List<MovementResponseDTO> getAllMovementsByAccountId(Integer accountId) {
        ModelMapper modelMapper = new ModelMapper();
        return movementRepository.findAllByAccountId(accountId)
                .stream()
                .map(movements -> modelMapper.map(movements, MovementResponseDTO.class))
                .collect(Collectors.toList());
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
        if (accountResponseDTO.getInitialBalance() == 0 && movementRequestDTO.getMovementType().equals("debito")) {
            throw new RuntimeException(); //TODO Añadir el mensaje de saldo no disponible idea usar un exception
        }
        if (movementRequestDTO.getMovementType().equals("debito") && movementRequestDTO.getValue() > accountResponseDTO.getInitialBalance()) {
            throw new RuntimeException(); //TODO Añadir el mensaje de saldo no disponible idea usar un exception
        }
        if (movementRequestDTO.getMovementType().equals("debito")){
            MAX_AMOUNT_PER_DAY += movementRequestDTO.getValue();
            newBalance = accountResponseDTO.getInitialBalance() - movementRequestDTO.getValue();
        }
        if (movementRequestDTO.getMovementType().equals("credito")){
            newBalance = accountResponseDTO.getInitialBalance() + movementRequestDTO.getValue();
        }
        movementRequestDTO.setBalance(newBalance);
        accountResponseDTO.setInitialBalance(newBalance);
    }

}
