package com.devsu.bank.service;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;

import java.util.List;

public interface IMovementService {

    MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO);
    List<MovementResponseDTO> getAllMovementsByAccountId(Integer accountId);
    List<MovementResponseDTO> getAllMovements();
    MovementResponseDTO getMovementById(Integer movementId);
    void deleteMovementById(Integer movementId);

}
