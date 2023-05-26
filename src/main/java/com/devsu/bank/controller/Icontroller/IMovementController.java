package com.devsu.bank.controller.Icontroller;

import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.exception.BankException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.devsu.bank.utils.AccountConstants.API;
import static com.devsu.bank.utils.MovementConstants.BASE_MOVEMENT_PATH;

@RequestMapping(API)
public interface IMovementController {
    ResponseEntity<?> createMovement(MovementRequestDTO movementRequestDTO) throws BankException;
    ResponseEntity<?> getAllMovements();
    ResponseEntity<?> getMovementById(Integer movementId);
    ResponseEntity<?> deleteMovementByID(Integer movementId);
}
