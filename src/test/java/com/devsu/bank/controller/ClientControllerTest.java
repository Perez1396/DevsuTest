package com.devsu.bank.controller;

import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.service.IAccountService;
import com.devsu.bank.service.IClientService;
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

import static com.devsu.bank.testUtils.BankHelper.BASE_PATH_CLIENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IClientService clientService;

    @Test
    void getClientsReturnsOk() throws Exception {
        List<ClientResponseDTO> mockClientList = new ArrayList<>();
        ClientResponseDTO client1 = BankHelper.createClientResponse();
        mockClientList.add(client1);
        when(clientService.getAllClients()).thenReturn(mockClientList);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH_CLIENT)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getClientByIdReturnsOk() throws Exception {
        ClientResponseDTO mockClientResponse = BankHelper.createClientResponse();
        when(clientService.getClientById(1)).thenReturn(mockClientResponse);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getClientByIdReturnsNotFoundStatus() throws Exception {
        when(clientService.getClientById(1)).thenReturn(null);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void createClientReturnsCreated() throws Exception {
        ClientRequestDTO clientRequestDTO = BankHelper.createClientRequest();
        String requestBody = objectMapper.writeValueAsString(clientRequestDTO);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        result.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deleteClientByIdReturnsOkStatus() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        verify(clientService).deleteClientByID(1);
    }

    @Test
    void putClientReturnsOk() throws Exception {
        ClientRequestDTO clientRequestDTO = BankHelper.createClientRequest();
        String requestBody = objectMapper.writeValueAsString(clientRequestDTO);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void patchClientReturnsOkStatus() throws Exception {
        ClientRequestDTO clientRequestDTO = BankHelper.createClientRequest();
        String requestBody = objectMapper.writeValueAsString(clientRequestDTO);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.patch("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
}