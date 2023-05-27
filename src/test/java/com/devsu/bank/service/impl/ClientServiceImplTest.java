package com.devsu.bank.service.impl;

import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.model.Client;
import com.devsu.bank.repository.ClientRepository;
import com.devsu.bank.testUtils.ClientHelper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateClientSuccess()  {
        ClientRequestDTO clientRequestDTO = ClientHelper.createClientRequest();
        Client client = ClientHelper.createClient();
        ClientResponseDTO clientResponseDTO = ClientHelper.createClientResponse();
        when(modelMapper.map(clientRequestDTO, Client.class)).thenReturn(client);
        when(clientRepository.save(ArgumentMatchers.any(Client.class))).thenReturn(client);
        when(modelMapper.map(client, ClientResponseDTO.class)).thenReturn(clientResponseDTO);
        ClientResponseDTO response = clientService.createClient(clientRequestDTO);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), client.getId());
    }

    @Test
    void testGetClientByID() {
        ClientResponseDTO clientResponseDTO = ClientHelper.createClientResponse();
        Client client = ClientHelper.createClient();
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(modelMapper.map(client, ClientResponseDTO.class)).thenReturn(clientResponseDTO);
        ClientResponseDTO response = clientService.getClientById(1);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), client.getId());
    }

    @Test
    void testGetAllClients() {
        List<ClientResponseDTO> clientResponseDTOList = new ArrayList<>();
        ClientResponseDTO clientResponseDTO = ClientHelper.createClientResponse();
        clientResponseDTOList.add(clientResponseDTO);
        Client client = ClientHelper.createClient();
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        when(clientRepository.findAll()).thenReturn(clients);
        when(modelMapper.map(client, ClientResponseDTO.class)).thenReturn(clientResponseDTO);
        List<ClientResponseDTO> response = clientService.getAllClients();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.size(), clientResponseDTOList.size());
    }

    @Test
    void testDeleteClientByID() {
        ClientResponseDTO clientResponseDTO = ClientHelper.createClientResponse();
        Client client = ClientHelper.createClient();
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(modelMapper.map(client, ClientResponseDTO.class)).thenReturn(clientResponseDTO);
        clientService.deleteClientByID(client.getId());
        verify(clientRepository, times(1)).deleteById(client.getId());
    }

    @Test
    void testPutClientByID() {
        ClientRequestDTO clientRequestDTO = ClientHelper.createClientRequest();
        ClientResponseDTO clientResponseDTO = ClientHelper.createClientResponse();
        Client client = ClientHelper.createClient();
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(modelMapper.map(client, ClientResponseDTO.class)).thenReturn(clientResponseDTO);
        when(clientRepository.save(ArgumentMatchers.any(Client.class))).thenReturn(client);
        when(modelMapper.map(client, ClientResponseDTO.class)).thenReturn(clientResponseDTO);
        ClientResponseDTO response = clientService.putClient(client.getId(), clientRequestDTO);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), client.getId());
    }

    @Test
    void testPatchClientByID() {
        ClientRequestDTO clientRequestDTO = ClientHelper.createClientRequest();
        ClientResponseDTO clientResponseDTO = ClientHelper.createClientResponse();
        Client client = ClientHelper.createClient();
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(modelMapper.map(client, ClientResponseDTO.class)).thenReturn(clientResponseDTO);
        when(clientRepository.save(ArgumentMatchers.any(Client.class))).thenReturn(client);
        when(modelMapper.map(client, ClientResponseDTO.class)).thenReturn(clientResponseDTO);
        ClientResponseDTO response = clientService.patchClient(client.getId(), clientRequestDTO);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), client.getId());
    }
}