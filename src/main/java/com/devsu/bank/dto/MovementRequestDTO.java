package com.devsu.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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

    @Override
    public String toString() {
        return "MovementRequestDTO{" +
                "movementType='" + movementType + '\'' +
                ", balance=" + balance +
                ", value=" + value +
                ", accountId=" + accountId +
                '}';
    }
}
