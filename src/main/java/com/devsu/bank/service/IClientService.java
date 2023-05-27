package com.devsu.bank.service;

import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;

import java.util.List;

public interface IClientService {

    /**
     *
     * @param clientRequestDTO DTO object that will be mapped into an entity for saving in database
     * @return DTO that was mapped after saving
     */
    ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);

    /**
     *
     * @param idClient id to perform the action in the database
     * @return DTO with the data needed
     */
    ClientResponseDTO getClientById(Integer idClient);

    /**
     *
     * @return A list of multiples DTO objects with all the data
     */
    List<ClientResponseDTO> getAllClients();

    /**
     *
     * @param idClient id to delete a client
     */
    void deleteClientByID(Integer idClient);

    /**
     *
     * @param idClient id for looking the existing data and make the update
     * @param clientRequestDTO DTO with the new data
     * @return
     */
    ClientResponseDTO patchClient(Integer idClient, ClientRequestDTO clientRequestDTO);

    /**
     *
     * @param idClient id for looking the existing data and make the update
     * @param clientRequestDTO DTO with the new data
     * @return
     */
    ClientResponseDTO putClient(Integer idClient, ClientRequestDTO clientRequestDTO);
}
