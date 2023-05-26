package com.devsu.bank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ReportResponseDTO {
    @JsonProperty("Fecha")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @JsonProperty("Cliente")
    private String name;
    @JsonProperty("NumeroCuenta")
    private String accountNumber;
    @JsonProperty("Tipo")
    private String movementType;
    @JsonProperty("SaldoInicial")
    private Integer initialBalance;
    @JsonProperty("Estado")
    private String state;
    @JsonProperty("Movimiento")
    private String Movimiento;
    @JsonProperty("SaldoDisponible")
    private Integer balance;


}
