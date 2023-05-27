package com.devsu.bank.service;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.ReportResponseDTO;
import com.devsu.bank.exception.BankException;

import java.util.List;

public interface IAccountService {
    /**
     * @param accountRequestDTO DTO object that will be mapped into an entity for saving in database
     * @return DTO that was mapped after saving
     */
    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) throws BankException;

    /**
     * @param accountId id to perform the action in the database
     * @return DTO with the data needed
     */
    AccountResponseDTO getAccountById(Integer accountId);

    /**
     * @return A list of multiples DTO objects with all the data
     */
    List<AccountResponseDTO> getAllAccounts();

    /**
     *
     * @param accountId id to delete an account
     */
    void deleteAccountById(Integer accountId);

    /**
     *
     * @param accountId id for looking the existing data and make the update
     * @param accountRequestDTO DTO with the new data
     * @return the updated object
     */
    AccountResponseDTO patchBalanceAccount(Integer accountId, AccountRequestDTO accountRequestDTO);

    /**
     *
     * @param clientId id for looking the existing data and retrieve the info needed
     * @param dateRange list of date ranges for creating the report
     * @return the report regarding the criteria entered
     */
    List<ReportResponseDTO> getAccountsForReport(Integer clientId, List<String> dateRange);

}
