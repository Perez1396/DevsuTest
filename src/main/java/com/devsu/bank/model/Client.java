package com.devsu.bank.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CLIENTS")
@Data
public class Client extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificacion")
    private Integer id;

    @NotNull
    @Column(name = "contraseña")
    private String password;

    @NotNull
    @Column(name = "estado")
    private String state;
}
