package com.devsu.bank.service.impl;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.model.Movements;
import com.devsu.bank.repository.MovementRepository;
import com.devsu.bank.service.IAccountService;
import com.devsu.bank.service.IMovementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovementServiceImpl implements IMovementService {
    @Autowired
    MovementRepository movementRepository;

    @Autowired
    IAccountService accountService;

    @Override
    public MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO) {
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(movementRequestDTO.getAccountId());
        if (accountResponseDTO == null) {
            return null;
        }
        return addMovementToAccount(accountResponseDTO, movementRequestDTO);
    }

    private MovementResponseDTO addMovementToAccount(AccountResponseDTO accountResponseDTO, MovementRequestDTO movementRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        //List<Movements> movementsList = accountResponseDTO.getMovementsList();

        calculateNewBalance(accountResponseDTO, movementRequestDTO);
        Movements movements = modelMapper.map(movementRequestDTO, Movements.class);
        movements.setDate(LocalDate.now());
        accountService.patchBalanceAccount(accountResponseDTO.getId(), modelMapper.map(accountResponseDTO, AccountRequestDTO.class));
        //movementsList.add(movements);
        return modelMapper.map(movementRepository.save(movements), MovementResponseDTO.class);
    }

    private void calculateNewBalance(AccountResponseDTO accountResponseDTO, MovementRequestDTO movementRequestDTO) {
        if (accountResponseDTO.getInitialBalance() == 0 && movementRequestDTO.getMovementType().equals("debito")) {
            throw new RuntimeException(); //TODO Añadir el mensaje de saldo no disponible idea usar un exception
        }

        if (movementRequestDTO.getMovementType().equals("debito") && movementRequestDTO.getValue() > accountResponseDTO.getInitialBalance()) {
            throw new RuntimeException(); //TODO Añadir el mensaje de saldo no disponible idea usar un exception
        }
        Integer newBalance = accountResponseDTO.getInitialBalance() + movementRequestDTO.getValue();
        movementRequestDTO.setBalance(newBalance);
        accountResponseDTO.setInitialBalance(newBalance);
    }

}
