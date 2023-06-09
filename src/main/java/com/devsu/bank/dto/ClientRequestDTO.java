package com.devsu.bank.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;

@Getter
@Setter
public class ClientRequestDTO {
    @JsonProperty("contraseña")
    private String password;
    @JsonProperty("estado")
    private String state;
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("genero")
    private String gender;
    @JsonProperty("edad")
    @Max(100)
    private Integer age;
    @JsonProperty("direccion")
    private String address;
    @JsonProperty("telefono")
    private String phone;

    @Override
    public String toString() {
        return "ClientRequestDTO{" +
                "password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
