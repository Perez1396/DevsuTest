package com.devsu.bank.controller.Icontroller;

import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.exception.BankException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.devsu.bank.utils.AccountConstants.API;
import static com.devsu.bank.utils.MovementConstants.BASE_MOVEMENT_PATH;

@RequestMapping(API)
@Api(tags = "Movement controller")
public interface IMovementController {

    @ApiOperation(value = "Retrieve the report of the all movements regarding the id client and date ranges given.")
    ResponseEntity<?> createMovement(@ApiParam(value = "Body with data to create a movement.", required = true) MovementRequestDTO movementRequestDTO) throws BankException;

    @ApiOperation(value = "Retrieve all the movements in the DB.")
    ResponseEntity<?> getAllMovements();

    @ApiOperation(value = "Retrieve a movement given an id.")
    ResponseEntity<?> getMovementById(@ApiParam(value = "Id for retrieving a movement.", required = true) Integer movementId);

    @ApiOperation(value = "Delete a movement given an id.")
    ResponseEntity<?> deleteMovementByID(@ApiParam(value = "Id to delete a movement.", required = true) Integer movementId);
}
