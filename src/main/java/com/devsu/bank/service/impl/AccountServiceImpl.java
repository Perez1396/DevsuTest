package com.devsu.bank.service.impl;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.model.Account;
import com.devsu.bank.model.Client;
import com.devsu.bank.model.Movements;
import com.devsu.bank.repository.AccountRepository;
import com.devsu.bank.service.IAccountService;
import com.devsu.bank.service.IClientService;
import com.devsu.bank.service.IMovementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IClientService clientService;
    @Autowired
    IMovementService movementService;

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountRequestDTO, Account.class);
        Client client = modelMapper.map(clientService.getClientById(accountRequestDTO.getClientId()), Client.class);
        account.setClient(client);
        account.setId(null);
        return modelMapper.map(accountRepository.save(account), AccountResponseDTO.class);
    }

    @Override
    public AccountResponseDTO getAccountById(Integer accountId) {
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
        AccountResponseDTO accountResponseDTO = getAccountById(accountId);
        if (accountResponseDTO != null) {
            accountRepository.deleteById(accountId);
        }
    }

    @Override
    public AccountResponseDTO patchBalanceAccount(Integer accountId, AccountRequestDTO accountRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        AccountResponseDTO accountResponseDTO = getAccountById(accountId);
        if (accountResponseDTO != null) {
            accountResponseDTO.setInitialBalance(accountRequestDTO.getInitialBalance());
            accountRepository.save(modelMapper.map(accountResponseDTO, Account.class));
        }
        return accountResponseDTO;
    }
}
