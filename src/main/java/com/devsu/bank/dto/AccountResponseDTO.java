package com.devsu.bank.dto;

import com.devsu.bank.model.Client;
import com.devsu.bank.model.Movements;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountResponseDTO {
    private Integer id;
    @JsonProperty("numeroCuenta")
    private Long accountNumber;
    @JsonProperty("tipo")
    private String accountType;
    @JsonProperty("saldoInicial")
    private Integer initialBalance;
    @JsonProperty("estado")
    private String state;
    @JsonProperty("cliente")
    private Client client;
    @JsonProperty("movimientos")
    private List<MovementResponseDTO> movementsList;
}
