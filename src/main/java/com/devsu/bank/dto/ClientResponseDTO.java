package com.devsu.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponseDTO {
    private Integer id;
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

    @Override
    public String toString() {
        return "ClientResponseDTO{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
