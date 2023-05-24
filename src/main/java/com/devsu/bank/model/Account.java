package com.devsu.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ACCOUNT")
@Data
public class Account {
    @Id
    @Column(name = "numeroCuenta")
    private Integer accountNumber;

    @NotNull
    @Column(name = "tipo")
    private String accountType;

    @NotNull
    @Column(name = "saldoInicial")
    private String initialBalance;

    @NotNull
    @Column(name = "estado")
    private String state;

}
