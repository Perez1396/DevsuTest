package com.devsu.bank.testUtils;

import com.devsu.bank.dto.ClientRequestDTO;
import com.devsu.bank.dto.ClientResponseDTO;
import com.devsu.bank.model.Client;

public class ClientHelper {

    public static final String BASE_PATH_CLIENT = "/api/clientes";
    public static final String NO_RECORDS_FOUND = "No hay registros asociados al id ingresado";

    public static ClientRequestDTO createClientRequest() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO();
        clientRequestDTO.setAddress("DA12345GDF");
        clientRequestDTO.setAge(25);
        clientRequestDTO.setName("Juan");
        clientRequestDTO.setPhone("12345");
        clientRequestDTO.setGender("Masculino");
        clientRequestDTO.setPassword("12333");
        clientRequestDTO.setState("True");
        return clientRequestDTO;
    }

    public static ClientResponseDTO createClientResponse() {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        clientResponseDTO.setId(1);
        clientResponseDTO.setAddress("DA12345GDF");
        clientResponseDTO.setAge(25);
        clientResponseDTO.setName("Juan");
        clientResponseDTO.setPhone("12345");
        clientResponseDTO.setGender("Masculino");
        clientResponseDTO.setPassword("12333");
        clientResponseDTO.setState("True");
        return clientResponseDTO;
    }

    public static Client createClient() {
        Client client = new Client();
        client.setId(1);
        client.setAddress("DA12345GDF");
        client.setAge(String.valueOf(25));
        client.setName("Juan");
        client.setPhone("12345");
        client.setGender("Masculino");
        client.setPassword("12333");
        client.setState("True");
        return client;
    }
}
