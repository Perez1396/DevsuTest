package com.devsu.bank.controller.Icontroller;

import com.devsu.bank.dto.ClientRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.devsu.bank.utils.AccountConstants.API;


@RequestMapping(API)
@Api(tags = "Client controller")
public interface IClientController {

    @ApiOperation(value = "Retrieve all the clients.")
    ResponseEntity<?> getClients();

    @ApiOperation(value = "Retrieve the client regarding the id entered.")
    ResponseEntity<?> getClientById(@ApiParam(value = "Id to retrieve the client.", required = true) Integer idClient);

    @ApiOperation(value = "Create the client with all the data in the requestDTO.")
    ResponseEntity<?> createClient(@ApiParam(value = "Body of the client entity with the information required for saving.", required = true) ClientRequestDTO clientRequestDTO);

    @ApiOperation(value = "Delete a client by its id.")
    ResponseEntity<?> deleteClientById(@ApiParam(value = "Id to delete the client.", required = true) Integer idClient);

    @ApiOperation(value = "Update all the client object.")
    ResponseEntity<?> putClient(@ApiParam(value = "Id to retrieve the client.", required = true)Integer idClient,
                                @ApiParam(value = "Body with the new data to make the update.", required = true) ClientRequestDTO clientRequestDTO);

    @ApiOperation(value = "Update the password of the client.")
    ResponseEntity<?> patchClient(@ApiParam(value = "Id to retrieve the client.", required = true)Integer idClient,
                                  @ApiParam(value = "Body with the new data to make the update.", required = true) ClientRequestDTO clientRequestDTO);
}
