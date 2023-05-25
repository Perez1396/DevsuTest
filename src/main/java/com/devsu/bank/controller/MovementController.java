package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IMovementController;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.service.IMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.devsu.bank.utils.ClientConstants.NO_RECORDS_FOUND;
import static com.devsu.bank.utils.MovementConstants.PATH_MOVEMENT_ID;

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

    @Override
    @GetMapping(PATH_MOVEMENT_ID)
    public ResponseEntity<?> getAllMovementsByAccountId(@PathVariable Integer accountId) {
        List<MovementResponseDTO> movementResponseDTOList = movementService.getAllMovementsByAccountId(accountId);
        return movementResponseDTOList.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(NO_RECORDS_FOUND)
                : ResponseEntity.ok(movementResponseDTOList);
    }
}
