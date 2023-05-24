package com.devsu.bank.controller.Icontroller;

import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.devsu.bank.utils.ClientConstants.BASE_PATH;
import static com.devsu.bank.utils.ClientConstants.PATH_CLIENT_ID;


@RequestMapping(BASE_PATH)
public interface IClientController {

    ResponseEntity<?> getClients();

    ResponseEntity<?> getClientById(Integer idClient);

    ResponseEntity<?> createClient(ClientRequestDTO clientRequestDTO);

    ResponseEntity<?> deleteClientById(Integer idClient);

    ResponseEntity<?> updateClient(Integer idClient, ClientRequestDTO clientRequestDTO);

    ResponseEntity<?> updateDataFromClient(Integer idClient, ClientRequestDTO clientRequestDTO);
}
