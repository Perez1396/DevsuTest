package com.devsu.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponseDTO {
    private Integer id;
    private String state;
    private String name;
    private String address;
    private String phone;
}
