package com.devsu.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDTO {
    @JsonProperty("numeroCuenta")
    private Long accountNumber;
    @JsonProperty("tipo")
    private String accountType;
    @JsonProperty("saldoInicial")
    private Integer initialBalance;
    @JsonProperty("estado")
    private String state;
    @JsonProperty("clienteId")
    private Integer clientId;
}
