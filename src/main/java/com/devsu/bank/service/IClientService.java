package com.devsu.bank.service;

import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;

import java.util.List;

public interface IClientService {

    ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);
    ClientResponseDTO getClientById(Integer idClient);

    List<ClientResponseDTO> getAllClients();
    void deleteClientByID(Integer idClient);
    ClientResponseDTO patchClient(Integer idClient, ClientRequestDTO clientRequestDTO);
}
