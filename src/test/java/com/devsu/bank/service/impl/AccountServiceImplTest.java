package com.devsu.bank.service.impl;

import com.devsu.bank.dto.*;
import com.devsu.bank.exception.BankException;
import com.devsu.bank.model.Account;
import com.devsu.bank.model.Client;
import com.devsu.bank.repository.AccountRepository;
import com.devsu.bank.testUtils.BankHelper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ClientServiceImpl clientService;
    @Mock
    private MovementServiceImpl movementService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createAccount() throws BankException {
        AccountRequestDTO accountRequestDTO = BankHelper.createAccountDTO();
        Account account = BankHelper.account();
        AccountResponseDTO accountResponseDTO = BankHelper.createAccountResponse();
        when(modelMapper.map(accountRequestDTO, Account.class)).thenReturn(account);
        when(clientService.getClientById(1)).thenReturn(BankHelper.createClientResponse());
        when(modelMapper.map(BankHelper.createClientResponse(), Client.class)).thenReturn(BankHelper.createClient());
        when(accountRepository.save(ArgumentMatchers.any(Account.class))).thenReturn(account);
        when(modelMapper.map(account, AccountResponseDTO.class)).thenReturn(accountResponseDTO);
        AccountResponseDTO response = accountService.createAccount(accountRequestDTO);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), account.getId());
    }

    @Test
    void getAccountById() {
        List<MovementResponseDTO> movementResponseDTOList = new ArrayList<>();
        Account account = BankHelper.account();
        AccountResponseDTO accountResponseDTO = BankHelper.createAccountResponse();
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(modelMapper.map(account, AccountResponseDTO.class)).thenReturn(accountResponseDTO);
        when(movementService.getAllMovementsByAccountId(1)).thenReturn(movementResponseDTOList);
        AccountResponseDTO response = accountService.getAccountById(1);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), account.getId());
    }

    @Test
    void getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        List<AccountResponseDTO> accountResponseDTOList = new ArrayList<>();
        Account account = BankHelper.account();
        accountList.add(account);
        AccountResponseDTO accountResponseDTO = BankHelper.createAccountResponse();
        accountResponseDTOList.add(accountResponseDTO);
        when(accountRepository.findAll()).thenReturn(accountList);
        when(modelMapper.map(account, AccountResponseDTO.class)).thenReturn(accountResponseDTO);
        List<AccountResponseDTO> response = accountService.getAllAccounts();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.size(), accountResponseDTOList.size());
    }

    @Test
    void deleteAccountById() {
        AccountRequestDTO accountRequestDTO = BankHelper.createAccountDTO();
        Account account = BankHelper.account();
        AccountResponseDTO accountResponseDTO = BankHelper.createAccountResponse();
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(modelMapper.map(account, AccountResponseDTO.class)).thenReturn(accountResponseDTO);
        accountService.deleteAccountById(account.getId());
        verify(accountRepository, times(1)).deleteById(account.getId());
    }
}