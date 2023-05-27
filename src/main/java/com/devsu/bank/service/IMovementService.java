package com.devsu.bank.service;

import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.exception.BankException;

import java.util.List;

public interface IMovementService {

    /**
     *
     * @param movementRequestDTO DTO object that will be mapped into an entity for saving in database
     * @return DTO that was mapped after saving
     * @throws BankException
     */
    MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO) throws BankException;

    /**
     *
     * @param accountId id of the account for making the search
     * @return a list of DTO object regarding the id given
     */
    List<MovementResponseDTO> getAllMovementsByAccountId(Integer accountId);

    /**
     *
     * @return A list of multiples DTO objects with all the data
     */
    List<MovementResponseDTO> getAllMovements();

    /**
     *
     * @param movementId id to perform the action in the database
     * @return DTO with the data needed
     */
    MovementResponseDTO getMovementById(Integer movementId);

    /**
     *
     * @param movementId id to delete an movementId
     */
    void deleteMovementById(Integer movementId);



}
