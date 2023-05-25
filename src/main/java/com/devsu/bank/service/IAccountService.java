package com.devsu.bank.service;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;

public interface IAccountService {
    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
    AccountResponseDTO getAccountById(Integer accountId);
    AccountResponseDTO patchBalanceAccount(Integer accountId, AccountRequestDTO accountRequestDTO);

}
