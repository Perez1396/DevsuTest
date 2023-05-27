package com.devsu.bank.controller.Icontroller;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.exception.BankException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.devsu.bank.utils.AccountConstants.API;

@RequestMapping(API)
@Api(tags = "Account controller")
public interface IAccountController {

    @ApiOperation(value = "Create the account with the requestDTO given.")
    ResponseEntity<?> createAccount(@ApiParam(value = "Body of the account entity with the information required for saving.", required = true) AccountRequestDTO accountRequestDTO) throws BankException;

    @ApiOperation(value = "Retrieve the account by its id.")
    ResponseEntity<?> getAccountById(@ApiParam(value = "Id of the account that will be retrieved.", required = true) Integer accountId);

    @ApiOperation(value = "Retrieve all the accounts.")
    ResponseEntity<?> getAllAccounts();

    @ApiOperation(value = "Delete an account by its id.")
    ResponseEntity<?> deleteAccountByID(@ApiParam(value = "Id of the account for being deleted.", required = true) Integer accountId);


}
