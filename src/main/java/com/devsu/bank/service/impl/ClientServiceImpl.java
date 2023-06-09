package com.devsu.bank.service.impl;

import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.model.Client;
import com.devsu.bank.repository.ClientRepository;
import com.devsu.bank.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        log.info("DTO to mapper into client entity: {}", clientRequestDTO.toString());
        ModelMapper modelMapper = new ModelMapper();
        Client client = modelMapper.map(clientRequestDTO, Client.class);
        return modelMapper.map(clientRepository.save(client), ClientResponseDTO.class);
    }

    @Override
    public ClientResponseDTO getClientById(Integer idClient) {
        log.info("Id client: {}", idClient);
        ModelMapper modelMapper = new ModelMapper();
        return clientRepository.findById(idClient)
                .map(client -> modelMapper.map(client, ClientResponseDTO.class))
                .orElse(null);
    }

    @Override
    public List<ClientResponseDTO> getAllClients() {
        ModelMapper modelMapper = new ModelMapper();
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream()
                .map(clients -> modelMapper.map(clients, ClientResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClientByID(Integer idClient) {
        log.info("Id client: {}", idClient);
        ClientResponseDTO clientResponseDTO = getClientById(idClient);
        if (clientResponseDTO != null){
            clientRepository.deleteById(idClient);
        }
    }

    @Override
    public ClientResponseDTO putClient(Integer idClient, ClientRequestDTO clientRequestDTO) {
        log.info("Id client: {}, DTO with new body for updating: {}", idClient, clientRequestDTO.toString());
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

    @Override
    public ClientResponseDTO patchClient(Integer idClient, ClientRequestDTO clientRequestDTO) {
        log.info("Id client: {}, DTO with new body for updating: {}", idClient, clientRequestDTO.toString());
        ModelMapper modelMapper = new ModelMapper();
        ClientResponseDTO clientResponseDTO = getClientById(idClient);
        if (clientResponseDTO != null){
            clientResponseDTO.setPassword(clientRequestDTO.getPassword());
            clientRepository.save(modelMapper.map(clientResponseDTO, Client.class));
        }
        return clientResponseDTO;
    }
}
