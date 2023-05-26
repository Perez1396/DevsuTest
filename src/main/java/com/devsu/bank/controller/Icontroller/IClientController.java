package com.devsu.bank.controller.Icontroller;

import com.devsu.bank.dto.ClientRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.devsu.bank.utils.AccountConstants.API;
import static com.devsu.bank.utils.ClientConstants.*;


@RequestMapping(API)
public interface IClientController {

    ResponseEntity<?> getClients();

    ResponseEntity<?> getClientById(Integer idClient);

    ResponseEntity<?> createClient(ClientRequestDTO clientRequestDTO);

    ResponseEntity<?> deleteClientById(Integer idClient);

    ResponseEntity<?> putClient(Integer idClient, ClientRequestDTO clientRequestDTO);

    ResponseEntity<?> patchClient(Integer idClient, ClientRequestDTO clientRequestDTO);
}
