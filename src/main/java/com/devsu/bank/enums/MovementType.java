package com.devsu.bank.enums;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;

@Getter
@AllArgsConstructor
public enum MovementType {
    CREDITO,
    DEBITO;

    @Override
    @JsonValue
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }
}
