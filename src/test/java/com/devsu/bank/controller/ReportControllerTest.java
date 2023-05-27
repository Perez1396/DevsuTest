package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IReportController;
import com.devsu.bank.dto.ReportResponseDTO;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ReportController.class)
@AutoConfigureMockMvc
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IAccountService accountService;

    @Test
    void getReportByClientAndDates_ReturnsOkStatus() throws Exception {
        List<ReportResponseDTO> mockResponse = new ArrayList<>();
        ReportResponseDTO reportResponseDTO = BankHelper.reportResponseDTO();
        mockResponse.add(reportResponseDTO);
        when(accountService.getAccountsForReport(1, Arrays.asList("2023-01-01", "2023-12-31"))).thenReturn(mockResponse);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/reportes/1")
                .param("fecha", "2023-01-01", "2023-12-31")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getReportByClientAndOneDate() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/reportes/1")
                .param("fecha", "2023-01-01")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }


}