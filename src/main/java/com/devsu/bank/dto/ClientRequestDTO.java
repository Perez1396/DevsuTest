package com.devsu.bank.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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
    private Integer age;
    @JsonProperty("direccion")
    private String address;
    @JsonProperty("telefono")
    private String phone;
}
