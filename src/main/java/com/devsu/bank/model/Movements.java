package com.devsu.bank.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MOVEMENTS")
@Data
public class Movements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name="valor")
    private Integer value;

    @NotNull
    @Column(name="tipoMovimiento")
    private String movementType;

    @Column(name="saldo")
    private Integer balance;

    @Column(name="fecha")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "idCuenta")
    private Account account;

    @Override
    public String toString() {
        return "Movements{" +
                "id=" + id +
                ", value=" + value +
                ", movementType='" + movementType + '\'' +
                ", balance=" + balance +
                ", date=" + date +
                ", account=" + account +
                '}';
    }
}
