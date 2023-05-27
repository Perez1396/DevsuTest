package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IAccountController;
import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.exception.BankException;
import com.devsu.bank.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.devsu.bank.utils.AccountConstants.BASE_ACCOUNT_PATH;
import static com.devsu.bank.utils.AccountConstants.PATH_ACCOUNT_ID;
import static com.devsu.bank.utils.ClientConstants.NO_RECORDS_FOUND;

@Slf4j
@RestController
public class AccountController implements IAccountController {

    @Autowired
    IAccountService accountService;

    @Override
    @PostMapping(BASE_ACCOUNT_PATH)
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) throws BankException {
        AccountResponseDTO accountResponseDTO = accountService.createAccount(accountRequestDTO);
        return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
    }

    @Override
    @GetMapping(BASE_ACCOUNT_PATH + PATH_ACCOUNT_ID)
    public ResponseEntity<?> getAccountById(@PathVariable Integer accountId) {
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(accountId);
        return accountResponseDTO == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(NO_RECORDS_FOUND) : ResponseEntity.ok(accountResponseDTO);
    }

    @Override
    @GetMapping(BASE_ACCOUNT_PATH)
    public ResponseEntity<?> getAllAccounts() {
        List<AccountResponseDTO> accountResponseDTO = accountService.getAllAccounts();
        log.info("List of accounts: {}", accountResponseDTO.toString());
        return new ResponseEntity<>(accountResponseDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(BASE_ACCOUNT_PATH + PATH_ACCOUNT_ID)
    public ResponseEntity<?> deleteAccountByID(@PathVariable Integer accountId) {
        accountService.deleteAccountById(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
