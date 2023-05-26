package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IMovementController;
import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.exception.BankException;
import com.devsu.bank.service.IMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.devsu.bank.utils.ClientConstants.NO_RECORDS_FOUND;
import static com.devsu.bank.utils.MovementConstants.BASE_MOVEMENT_PATH;
import static com.devsu.bank.utils.MovementConstants.PATH_MOVEMENT_ID;

@RestController
public class MovementController implements IMovementController {

    @Autowired
    IMovementService movementService;

    @Override
    @PostMapping(BASE_MOVEMENT_PATH)
    public ResponseEntity<?> createMovement(@RequestBody MovementRequestDTO movementRequestDTO) throws BankException {
        MovementResponseDTO movementResponseDTO = movementService.createMovement(movementRequestDTO);
        return new ResponseEntity<>(movementResponseDTO, HttpStatus.CREATED);
    }

    @Override
    @GetMapping(BASE_MOVEMENT_PATH)
    public ResponseEntity<?> getAllMovements() {
        List<MovementResponseDTO> movementResponseDTOList = movementService.getAllMovements();
        return new ResponseEntity<>(movementResponseDTOList, HttpStatus.OK);
    }

    @Override
    @GetMapping(BASE_MOVEMENT_PATH + PATH_MOVEMENT_ID)
    public ResponseEntity<?> getMovementById(@PathVariable Integer movementId) {
        MovementResponseDTO movementResponseDTO = movementService.getMovementById(movementId);
        return movementResponseDTO == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(NO_RECORDS_FOUND) : ResponseEntity.ok(movementResponseDTO);
    }

    @Override
    @DeleteMapping(BASE_MOVEMENT_PATH + PATH_MOVEMENT_ID)
    public ResponseEntity<?> deleteMovementByID(@PathVariable Integer movementId) {
        movementService.deleteMovementById(movementId);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
