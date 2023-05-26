package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IReportController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController implements IReportController {
    @Override
    public ResponseEntity<?> getReportByClientAndDates(@PathVariable Integer idClient,
                                                       @RequestParam(value = "fecha", required = false) List<String> dateRange) {
        return null;
    }
}
