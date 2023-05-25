package com.devsu.bank.service;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.ClientResponseDTO;

public interface IAccountService {
    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);

}
