package com.devsu.bank.service.impl;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.model.Account;
import com.devsu.bank.model.Client;
import com.devsu.bank.repository.AccountRepository;
import com.devsu.bank.service.IAccountService;
import com.devsu.bank.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IClientService clientService;

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Client client = modelMapper.map(clientService.getClientById(accountRequestDTO.getClientId()), Client.class);
        Account account = modelMapper.map(accountRequestDTO, Account.class);
        account.setClient(client);
        return modelMapper.map(accountRepository.save(account), AccountResponseDTO.class);
    }
}
