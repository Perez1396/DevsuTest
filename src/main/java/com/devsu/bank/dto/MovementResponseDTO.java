package com.devsu.bank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovementResponseDTO {
    private Integer id;
    @JsonProperty("tipoMovimiento")
    private String movementType;
    @JsonProperty("saldo")
    private Integer balance;
    @JsonProperty("valor")
    private Integer value;
    @JsonProperty("fecha")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Override
    public String toString() {
        return "MovementResponseDTO{" +
                "id=" + id +
                ", movementType='" + movementType + '\'' +
                ", balance=" + balance +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
