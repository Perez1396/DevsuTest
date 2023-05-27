package com.devsu.bank.controller;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.service.IAccountService;
import com.devsu.bank.testUtils.BankHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IAccountService accountService;


    @Test
    void createAccountReturnsCreatedStatus() throws Exception {
        AccountRequestDTO accountRequestDTO = BankHelper.createAccountDTO();
        String requestBody = objectMapper.writeValueAsString(accountRequestDTO);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        result.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getAllAccountsReturnsOk() throws Exception {
        List<AccountResponseDTO> mockAccountList = new ArrayList<>();
        AccountResponseDTO account1 = BankHelper.createAccountResponse();
        mockAccountList.add(account1);
        when(accountService.getAllAccounts()).thenReturn(mockAccountList);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAccountByIdReturnsAccountResponse() throws Exception {
        AccountResponseDTO account1 = BankHelper.createAccountResponse();
        when(accountService.getAccountById(1)).thenReturn(account1);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/cuentas/1")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAccountByIdReturnsNotFoundStatus() throws Exception {
        when(accountService.getAccountById(1)).thenReturn(null);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/cuentas/1")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteAccountByIdReturnsOkStatus() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/cuentas/1")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        verify(accountService).deleteAccountById(1);
    }

}