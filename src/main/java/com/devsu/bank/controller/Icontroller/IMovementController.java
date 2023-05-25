package com.devsu.bank.controller.Icontroller;

import com.devsu.bank.dto.MovementRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.devsu.bank.utils.MovementConstants.BASE_MOVEMENT_PATH;

@RequestMapping(BASE_MOVEMENT_PATH)
public interface IMovementController {
    ResponseEntity<?> createMovement(MovementRequestDTO movementRequestDTO);
    ResponseEntity<?> getAllMovementsByAccountId(Integer accountId);
}
