package com.devsu.bank.testUtils;

import com.devsu.bank.dto.*;
import com.devsu.bank.model.Account;
import com.devsu.bank.model.Client;
import com.devsu.bank.model.Movements;

import java.time.LocalDate;

public class BankHelper {

    public static final String BASE_PATH_CLIENT = "/api/clientes";

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

    public static Account account() {
        Account account = new Account();
        account.setId(1);
        account.setAccountType("Ahorro");
        account.setClient(createClient());
        account.setAccountNumber(12345L);
        account.setState("true");
        account.setInitialBalance(2000);
        return account;
    }

    public static AccountRequestDTO createAccountDTO() {
        AccountRequestDTO account = new AccountRequestDTO();
        account.setAccountType("Ahorro");
        account.setAccountNumber(12345L);
        account.setState("true");
        account.setInitialBalance(2000);
        account.setClientId(1);
        return account;
    }

    public static AccountResponseDTO createAccountResponse() {
        AccountResponseDTO account = new AccountResponseDTO();
        account.setAccountType("Ahorro");
        account.setAccountNumber(12345L);
        account.setState("true");
        account.setInitialBalance(2000);
        account.setClient(createClient());
        account.setId(1);
        account.setMovementsList(null);
        return account;
    }

    public static MovementRequestDTO movementRequestDTO(){
        MovementRequestDTO movementRequestDTO = new MovementRequestDTO();
        movementRequestDTO.setBalance(2500);
        movementRequestDTO.setMovementType("debito");
        movementRequestDTO.setValue(500);
        movementRequestDTO.setAccountId(1);
        return movementRequestDTO;
    }

    public static MovementResponseDTO movementResponseDTO(){
        MovementResponseDTO movementResponseDTO = new MovementResponseDTO();
        movementResponseDTO.setId(1);
        movementResponseDTO.setMovementType("debito");
        movementResponseDTO.setValue(500);
        movementResponseDTO.setBalance(2500);
        movementResponseDTO.setDate(LocalDate.now());
        return movementResponseDTO;
    }

    public static Movements movements(){
        Movements movements = new Movements();
        movements.setId(1);
        movements.setMovementType("debito");
        movements.setValue(500);
        movements.setBalance(2500);
        movements.setDate(LocalDate.now());
        return movements;
    }

    public static ReportResponseDTO reportResponseDTO(){
        ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
        reportResponseDTO.setBalance(3000);
        reportResponseDTO.setMovimiento("debito");
        reportResponseDTO.setState("True");
        reportResponseDTO.setName("Daniel");
        reportResponseDTO.setAccountType("Corriente");
        reportResponseDTO.setInitialBalance(5000);
        reportResponseDTO.setDate(LocalDate.now());
        reportResponseDTO.setAccountNumber(1235L);
        return reportResponseDTO;
    }

}
