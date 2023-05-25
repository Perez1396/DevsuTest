package com.devsu.bank.service;

import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;

public interface IMovementService {

    MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO);
}
