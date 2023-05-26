package com.devsu.bank.controller.Icontroller;

import com.devsu.bank.dto.AccountRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.devsu.bank.utils.AccountConstants.API;
import static com.devsu.bank.utils.AccountConstants.BASE_ACCOUNT_PATH;

@RequestMapping(API)
public interface IAccountController {

    ResponseEntity<?> createAccount(AccountRequestDTO accountRequestDTO);
    ResponseEntity<?> getAccountById(Integer accountId);
    ResponseEntity<?> getAllAccounts();
    ResponseEntity<?> deleteAccountByID(Integer accountId);


}
