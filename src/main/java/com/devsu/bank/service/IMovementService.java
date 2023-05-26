package com.devsu.bank.service;

import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.exception.BankException;

import java.util.List;

public interface IMovementService {

    MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO) throws BankException;
    List<MovementResponseDTO> getAllMovementsByAccountId(Integer accountId);
    List<MovementResponseDTO> getAllMovements();
    MovementResponseDTO getMovementById(Integer movementId);
    void deleteMovementById(Integer movementId);

}
