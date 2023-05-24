package com.devsu.bank.service.impl;

import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.model.Client;
import com.devsu.bank.repository.ClientRepository;
import com.devsu.bank.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Client client = modelMapper.map(clientRequestDTO, Client.class);
        return modelMapper.map(clientRepository.save(client), ClientResponseDTO.class);
    }
}
