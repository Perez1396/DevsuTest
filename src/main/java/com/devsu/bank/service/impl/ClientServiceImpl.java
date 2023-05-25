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

    @Override
    public ClientResponseDTO getClientById(Integer idClient) {
        ModelMapper modelMapper = new ModelMapper();
        return clientRepository.findById(idClient)
                .map(client -> modelMapper.map(client, ClientResponseDTO.class))
                .orElse(null);
    }

    @Override
    public void deleteClientByID(Integer idClient) {
        ClientResponseDTO clientResponseDTO = getClientById(idClient);
        if (clientResponseDTO != null){
            clientRepository.deleteById(idClient);
        }
    }

    @Override
    public ClientResponseDTO patchClient(Integer idClient, ClientRequestDTO clientRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ClientResponseDTO clientResponseDTO = getClientById(idClient);
        if (clientResponseDTO != null){
            clientResponseDTO.setAddress(clientRequestDTO.getAddress());
            clientResponseDTO.setName(clientRequestDTO.getName());
            clientResponseDTO.setState(clientRequestDTO.getState());
            clientResponseDTO.setPhone(clientRequestDTO.getPhone());
            clientRepository.save(modelMapper.map(clientResponseDTO, Client.class));
        }
        return clientResponseDTO;
    }
}
