package com.devsu.bank.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;


@Getter
@Setter
public class Person {
    @Column(name="nombre")
    private String name;
    @Column(name="genero")
    private String gender;
    @Column(name="edad")
    private String age;
    @Column(name="direccion")
    private String address;
    @Column(name="telefono")
    private String phone;
}
