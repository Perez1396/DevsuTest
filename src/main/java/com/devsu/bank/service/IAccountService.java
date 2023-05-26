package com.devsu.bank.service;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.ReportResponseDTO;

import java.util.List;

public interface IAccountService {
    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
    AccountResponseDTO getAccountById(Integer accountId);
    List<AccountResponseDTO> getAllAccounts();
    void deleteAccountById(Integer accountId);
    AccountResponseDTO patchBalanceAccount(Integer accountId, AccountRequestDTO accountRequestDTO);
    List<ReportResponseDTO> getAccountsForReport(Integer clientId, List<String> dateRange);

}
