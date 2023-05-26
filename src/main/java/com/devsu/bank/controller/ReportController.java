package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IReportController;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.ReportResponseDTO;
import com.devsu.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.devsu.bank.utils.ClientConstants.PATH_CLIENT_ID;
import static com.devsu.bank.utils.ReportConstants.BASE_REPORT_PATH;

@RestController
public class ReportController implements IReportController {

    @Autowired
    IAccountService accountService;
    @Override
    @GetMapping(BASE_REPORT_PATH + PATH_CLIENT_ID)
    public ResponseEntity<?> getReportByClientAndDates(@PathVariable Integer idClient,
                                                       @RequestParam(value = "fecha", required = false) List<String> dateRange) {
        List<ReportResponseDTO> reportResponseDTOList = accountService.getAccountsForReport(idClient, dateRange);
        return new ResponseEntity<>(reportResponseDTOList, HttpStatus.OK);
    }
}
