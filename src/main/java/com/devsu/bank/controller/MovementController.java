package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IMovementController;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.service.IMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovementController implements IMovementController {

    @Autowired
    IMovementService movementService;

    @Override
    @PostMapping
    public ResponseEntity<?> createMovement(@RequestBody MovementRequestDTO movementRequestDTO) {
        MovementResponseDTO movementResponseDTO = movementService.createMovement(movementRequestDTO);
        return new ResponseEntity<>(movementResponseDTO, HttpStatus.CREATED);
    }
}
