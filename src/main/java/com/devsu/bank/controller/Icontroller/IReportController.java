package com.devsu.bank.controller.Icontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.devsu.bank.utils.AccountConstants.API;

@RequestMapping(API)
@Api(tags = "Report controller")
public interface IReportController {

    @ApiOperation(value = "Retrieve the report of the all movements regarding the id client and date ranges given.")
    ResponseEntity<?> getReportByClientAndDates(@ApiParam(value = "Id of the client for being retrieved.", required = true) Integer idClient,
                                                @ApiParam(value = "A list of a date range.", required = true)List<String> dateRange);

}
