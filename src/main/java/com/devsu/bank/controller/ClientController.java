package com.devsu.bank.controller;

import com.devsu.bank.controller.Icontroller.IClientController;
import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.devsu.bank.utils.ClientConstants.*;

@Slf4j
@RestController
public class ClientController implements IClientController {

    @Autowired
    IClientService clientService;

    @Override
    @GetMapping(BASE_CLIENT_PATH)
    public ResponseEntity<?> getClients() {
        List<ClientResponseDTO> clientResponseDTO = clientService.getAllClients();
        log.info("List of clients: {}", clientResponseDTO.toString());
        return new ResponseEntity<>(clientResponseDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(BASE_CLIENT_PATH + PATH_CLIENT_ID)
    public ResponseEntity<?> getClientById (@PathVariable Integer idClient) {
        ClientResponseDTO clientResponseDTO = clientService.getClientById(idClient);
        return clientResponseDTO == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(NO_RECORDS_FOUND) : ResponseEntity.ok(clientResponseDTO);
    }

    @Override
    @PostMapping(BASE_CLIENT_PATH)
    public ResponseEntity<?> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.createClient(clientRequestDTO);
        return new ResponseEntity<>(clientResponseDTO, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(BASE_CLIENT_PATH + PATH_CLIENT_ID)
    public ResponseEntity<?> deleteClientById (@PathVariable Integer idClient) {
        clientService.deleteClientByID(idClient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PutMapping(BASE_CLIENT_PATH + PATH_CLIENT_ID)
    public ResponseEntity<?> putClient (@PathVariable Integer idClient, @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.putClient(idClient, clientRequestDTO);
        return new ResponseEntity<>(clientResponseDTO, HttpStatus.OK);
    }

    @Override
    @PatchMapping(BASE_CLIENT_PATH + PATH_CLIENT_ID)
    public ResponseEntity<?> patchClient (@PathVariable Integer idClient, @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.patchClient(idClient, clientRequestDTO);
        return new ResponseEntity<>(clientResponseDTO, HttpStatus.OK);
    }
}
