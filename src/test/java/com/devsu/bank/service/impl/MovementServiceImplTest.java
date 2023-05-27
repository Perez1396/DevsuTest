package com.devsu.bank.service.impl;

import com.devsu.bank.dto.AccountRequestDTO;
import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.MovementRequestDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.exception.BankException;
import com.devsu.bank.model.Account;
import com.devsu.bank.model.Movements;
import com.devsu.bank.repository.AccountRepository;
import com.devsu.bank.repository.MovementRepository;
import com.devsu.bank.testUtils.BankHelper;
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

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovementServiceImplTest {

    @Mock
    private MovementRepository movementRepository;
    @Mock
    private ClientServiceImpl clientService;
    @Mock
    private AccountServiceImpl accountService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private MovementServiceImpl movementService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getMovementById() {
        Movements movements = BankHelper.movements();
        MovementResponseDTO movementResponseDTO = BankHelper.movementResponseDTO();
        when(movementRepository.findById(1)).thenReturn(Optional.of(movements));
        when(modelMapper.map(movements, MovementResponseDTO.class)).thenReturn(movementResponseDTO);
        MovementResponseDTO response = movementService.getMovementById(1);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), movements.getId());
    }

    @Test
    void getAllAccounts() {
        List<Movements> movementsList = new ArrayList<>();
        List<MovementResponseDTO> movementResponseDTOList = new ArrayList<>();
        Movements movements = BankHelper.movements();
        movementsList.add(movements);
        MovementResponseDTO movementResponseDTO = BankHelper.movementResponseDTO();
        movementResponseDTOList.add(movementResponseDTO);
        when(movementRepository.findAll()).thenReturn(movementsList);
        when(modelMapper.map(movements, MovementResponseDTO.class)).thenReturn(movementResponseDTO);
        List<MovementResponseDTO> response = movementService.getAllMovements();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.size(), movementResponseDTOList.size());
    }

    @Test
    public void testCreateMovement() throws BankException {
        List<MovementResponseDTO> movementResponseDTOList = new ArrayList<>();
        MovementRequestDTO movementRequestDTO = BankHelper.movementRequestDTO();
        Movements movements = BankHelper.movements();
        MovementResponseDTO movementResponseDTO = BankHelper.movementResponseDTO();
        movementResponseDTOList.add(movementResponseDTO);
        AccountResponseDTO accountResponseDTO = BankHelper.createAccountResponse();
        accountResponseDTO.setMovementsList(movementResponseDTOList);
        AccountRequestDTO accountRequestDTO = BankHelper.createAccountDTO();
        when(accountService.getAccountById(1)).thenReturn(accountResponseDTO);
        when(modelMapper.map(movementRequestDTO, Movements.class)).thenReturn(movements);
        when(modelMapper.map(accountResponseDTO, AccountRequestDTO.class)).thenReturn(accountRequestDTO);
        when(accountService.patchBalanceAccount(1, accountRequestDTO)).thenReturn(accountResponseDTO);
        when(movementRepository.save(ArgumentMatchers.any(Movements.class))).thenReturn(movements);
        when(modelMapper.map(movements, MovementResponseDTO.class)).thenReturn(movementResponseDTO);
        MovementResponseDTO responseDTO = movementService.createMovement(movementRequestDTO);
        assertNotNull(responseDTO);
        verify(accountService).getAccountById(1);
        verify(movementRepository).save(ArgumentMatchers.any(Movements.class));
    }

    @Test
    void deleteMovementById() {
        MovementRequestDTO movementRequestDTO = BankHelper.movementRequestDTO();
        Movements movements = BankHelper.movements();
        MovementResponseDTO movementResponseDTO = BankHelper.movementResponseDTO();
        when(movementRepository.findById(1)).thenReturn(Optional.of(movements));
        when(modelMapper.map(movements, MovementResponseDTO.class)).thenReturn(movementResponseDTO);
        movementService.deleteMovementById(movements.getId());
        verify(movementRepository, times(1)).deleteById(movements.getId());
    }

}