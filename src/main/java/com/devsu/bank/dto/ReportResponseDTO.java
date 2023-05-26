package com.devsu.bank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReportResponseDTO {
    @JsonProperty("Fecha")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @JsonProperty("Cliente")
    private String name;
    @JsonProperty("NumeroCuenta")
    private Long accountNumber;
    @JsonProperty("Tipo")
    private String accountType;
    @JsonProperty("SaldoInicial")
    private Integer initialBalance;
    @JsonProperty("Estado")
    private String state;
    @JsonProperty("Movimiento")
    private String movimiento;
    @JsonProperty("SaldoDisponible")
    private Integer balance;


}
