package com.devsu.bank.controller.Icontroller;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.ClientRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.devsu.bank.utils.AccountConstants.BASE_ACCOUNT_PATH;

@RequestMapping(BASE_ACCOUNT_PATH)
public interface IAccountController {

    ResponseEntity<?> createAccount(AccountRequestDTO accountRequestDTO);
}
