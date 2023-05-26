package com.devsu.bank.controller.Icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.devsu.bank.utils.AccountConstants.API;

@RequestMapping(API)
public interface IReportController {
    ResponseEntity<?> getReportByClientAndDates(Integer idClient, List<String> dateRange);

}
