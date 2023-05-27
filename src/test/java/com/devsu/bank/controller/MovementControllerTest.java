package com.devsu.bank.controller;

import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.service.IMovementService;
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

import static org.mockito.Mockito.when;

@WebMvcTest(MovementController.class)
@AutoConfigureMockMvc
class MovementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IMovementService movementService;

    @Test
    void createMovementReturnsCreatedStatus() throws Exception {
        MovementRequestDTO movementRequestDTO = BankHelper.movementRequestDTO();
        String requestBody = objectMapper.writeValueAsString(movementRequestDTO);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/movimientos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        result.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getAllMovementsReturnsOkStatus() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/movimientos")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMovementByIdReturnsOkStatus() throws Exception {
        MovementResponseDTO mockResponse = BankHelper.movementResponseDTO();
        when(movementService.getMovementById(1)).thenReturn(mockResponse);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/movimientos/1")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMovementByIdReturnsNotFoundStatus() throws Exception {
        when(movementService.getMovementById(1)).thenReturn(null);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/movimientos/1")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteMovementByIdReturnsOkStatus() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/movimientos/1")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

}