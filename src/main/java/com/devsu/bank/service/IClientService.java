package com.devsu.bank.service;

import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;

public interface IClientService {

    ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);
}
