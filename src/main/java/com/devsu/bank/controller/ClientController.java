package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IClientController;
import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.devsu.bank.utils.ClientConstants.NO_RECORDS_FOUND;
import static com.devsu.bank.utils.ClientConstants.PATH_CLIENT_ID;

@RestController
public class ClientController implements IClientController {

    @Autowired
    IClientService clientService;

    @Override
    @GetMapping
    public ResponseEntity<?> getClients() {
        List<ClientResponseDTO> clientResponseDTO = clientService.getAllClients();
        return new ResponseEntity<>(clientResponseDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(PATH_CLIENT_ID)
    public ResponseEntity<?> getClientById (@PathVariable Integer idClient) {
        ClientResponseDTO clientResponseDTO = clientService.getClientById(idClient);
        return clientResponseDTO == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(NO_RECORDS_FOUND) : ResponseEntity.ok(clientResponseDTO);
    }

    @Override
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.createClient(clientRequestDTO);
        return new ResponseEntity<>(clientResponseDTO, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(PATH_CLIENT_ID)
    public ResponseEntity<?> deleteClientById (@PathVariable Integer idClient) {
        clientService.deleteClientByID(idClient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PutMapping(PATH_CLIENT_ID)
    public ResponseEntity<?> putClient (@PathVariable Integer idClient, @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.putClient(idClient, clientRequestDTO);
        return new ResponseEntity<>(clientResponseDTO, HttpStatus.OK);
    }

    @Override
    @PatchMapping(PATH_CLIENT_ID)
    public ResponseEntity<?> patchClient (@PathVariable Integer idClient, @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.patchClient(idClient, clientRequestDTO);
        return new ResponseEntity<>(clientResponseDTO, HttpStatus.OK);
    }
}
