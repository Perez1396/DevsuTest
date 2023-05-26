package com.devsu.bank.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ACCOUNT")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numeroCuenta")
    private Long accountNumber;

    @NotNull
    @Column(name = "tipo")
    private String accountType;

    @NotNull
    @Column(name = "saldoInicial")
    private Integer initialBalance;

    @NotNull
    @Column(name = "estado")
    private String state;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Client client;

}
