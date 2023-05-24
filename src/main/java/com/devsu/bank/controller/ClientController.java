package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IClientController;
import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.devsu.bank.utils.ClientConstants.PATH_CLIENT_ID;

@RestController
public class ClientController implements IClientController {

    @Autowired
    IClientService clientService;

    @Override
    @GetMapping
    public ResponseEntity<?> getClients() {
        return null;
    }

    @Override
    @GetMapping(PATH_CLIENT_ID)
    public ResponseEntity<?> getClientById (@PathVariable Integer idClient) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.createClient(clientRequestDTO);
        return new ResponseEntity<>(clientResponseDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(PATH_CLIENT_ID)
    public ResponseEntity<?> deleteClientById (@PathVariable Integer idClient) {
        return null;
    }

    @Override
    @PutMapping(PATH_CLIENT_ID)
    public ResponseEntity<?> updateClient (@PathVariable Integer idClient, @RequestBody ClientRequestDTO clientRequestDTO) {
        return null;
    }

    @Override
    @PatchMapping(PATH_CLIENT_ID)
    public ResponseEntity<?> updateDataFromClient (@PathVariable Integer idClient, @RequestBody ClientRequestDTO clientRequestDTO) {
        return null;
    }
}
