package com.devsu.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovementRequestDTO {
    @JsonProperty("tipoMovimiento")
    private String movementType;
    @JsonProperty("saldo")
    private Integer balance;
    @JsonProperty("valor")
    private Integer value;
    @JsonProperty("idCuenta")
    private Integer accountId;
}
