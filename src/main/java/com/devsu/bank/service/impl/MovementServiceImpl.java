package com.devsu.bank.service.impl;

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

@Service
public class MovementServiceImpl implements IMovementService {
    @Autowired
    MovementRepository movementRepository;

    @Autowired
    IAccountService accountService;

    @Override
    public MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(movementRequestDTO.getAccountId());
        if (accountResponseDTO == null) {
            return null;
        }

        Movements movements = modelMapper.map(movementRequestDTO, Movements.class);
        return modelMapper.map(movementRepository.save(movements), MovementResponseDTO.class);
    }

}
