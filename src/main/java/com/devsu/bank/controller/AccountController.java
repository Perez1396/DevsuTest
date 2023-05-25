package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IAccountController;
import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.devsu.bank.utils.AccountConstants.PATH_ACCOUNT_ID;
import static com.devsu.bank.utils.ClientConstants.NO_RECORDS_FOUND;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    IAccountService accountService;

    @Override
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO accountResponseDTO = accountService.createAccount(accountRequestDTO);
        return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
    }

    @Override
    @GetMapping(PATH_ACCOUNT_ID)
    public ResponseEntity<?> getAccountById(@PathVariable Integer accountId) {
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(accountId);
        if (accountResponseDTO == null) {
            return new ResponseEntity<>(NO_RECORDS_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountResponseDTO, HttpStatus.OK);
    }
}
