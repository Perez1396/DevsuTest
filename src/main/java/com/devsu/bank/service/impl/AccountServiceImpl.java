package com.devsu.bank.service.impl;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.dto.ReportResponseDTO;
import com.devsu.bank.exception.BankException;
import com.devsu.bank.mapper.ReportMapper;
import com.devsu.bank.model.Account;
import com.devsu.bank.model.Client;
import com.devsu.bank.repository.AccountRepository;
import com.devsu.bank.service.IAccountService;
import com.devsu.bank.service.IClientService;
import com.devsu.bank.service.IMovementService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.devsu.bank.utils.AccountConstants.NO_RECORDS_FOUND;
import static com.devsu.bank.utils.MovementConstants.MAX_AMOUNT_REACHED;

@Slf4j
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IClientService clientService;
    @Autowired
    IMovementService movementService;

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) throws BankException {
        log.info("DTO to mapper into account entity: {}", accountRequestDTO.toString());
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountRequestDTO, Account.class);
        ClientResponseDTO clientResponseDTO = clientService.getClientById(accountRequestDTO.getClientId());
        if (clientResponseDTO == null){
            throw new BankException(NO_RECORDS_FOUND, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        Client client = modelMapper.map(clientResponseDTO, Client.class);
        account.setClient(client);
        account.setId(null);
        return modelMapper.map(accountRepository.save(account), AccountResponseDTO.class);
    }

    @Override
    public AccountResponseDTO getAccountById(Integer accountId) {
        log.info("Id entered: {}", accountId);
        ModelMapper modelMapper = new ModelMapper();
        return accountRepository.findById(accountId)
                .map(account -> {
                    AccountResponseDTO accountResponseDTO = modelMapper.map(account, AccountResponseDTO.class);
                    accountResponseDTO.setMovementsList(movementService.getAllMovementsByAccountId(accountId));
                    return accountResponseDTO;
                })
                .orElse(null);
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        ModelMapper modelMapper = new ModelMapper();
        List<Account> movementsList = accountRepository.findAll();
        return movementsList.stream()
                .map(accounts -> modelMapper.map(accounts, AccountResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccountById(Integer accountId) {
        log.info("Id account: {}", accountId);
        AccountResponseDTO accountResponseDTO = getAccountById(accountId);
        if (accountResponseDTO != null) {
            accountRepository.deleteById(accountId);
        }
    }

    @Override
    public AccountResponseDTO patchBalanceAccount(Integer accountId, AccountRequestDTO accountRequestDTO) {
        log.info("Id of the account to verify that exists : {} and the new body: {}", accountId, accountRequestDTO.toString());
        ModelMapper modelMapper = new ModelMapper();
        AccountResponseDTO accountResponseDTO = getAccountById(accountId);
        if (accountResponseDTO != null) {
            accountResponseDTO.setInitialBalance(accountRequestDTO.getInitialBalance());
            accountRepository.save(modelMapper.map(accountResponseDTO, Account.class));
        }
        return accountResponseDTO;
    }

    @Override
    public List<ReportResponseDTO> getAccountsForReport(Integer clientId, List<String> dateRange) {
        log.info("Id of the client to generate the report: {} and the dates range: {}", clientId, dateRange);
        List<AccountResponseDTO> accountResponseDTOList = getAccountsByClientId(clientId);
        addMovementsToEachAccount(accountResponseDTOList);
        return ReportMapper.mapFromAccountResponseToReport(accountResponseDTOList, dateRange);
    }

    private void addMovementsToEachAccount(List<AccountResponseDTO> accountResponseDTOList) {
        accountResponseDTOList
                .forEach(accountResponseDTO ->
                        accountResponseDTO.setMovementsList(movementService.getAllMovementsByAccountId(accountResponseDTO.getId())));
    }


    private List<AccountResponseDTO> getAccountsByClientId(Integer clientId) {
        log.info("Id client: {}", clientId);
        ModelMapper modelMapper = new ModelMapper();
        List<Account> movementsList = accountRepository.findAllByClientId(clientId);
        return movementsList.stream()
                .map(accounts -> modelMapper.map(accounts, AccountResponseDTO.class))
                .collect(Collectors.toList());
    }
}
