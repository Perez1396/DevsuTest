package com.devsu.bank.mapper;

import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.dto.ReportResponseDTO;
import com.devsu.bank.enums.MovementType;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ReportMapper {

    public static List<ReportResponseDTO> mapFromAccountResponseToReport(List<AccountResponseDTO> accountResponseDTOList, List<String> dateRange){
        List<ReportResponseDTO> response = new ArrayList<>();
        LocalDate initialDate = LocalDate.parse(dateRange.get(0));
        LocalDate finalDate = dateRange.size() > 1 ? LocalDate.parse(dateRange.get(1)) : LocalDate.parse(dateRange.get(0));
        accountResponseDTOList.forEach(accountResponseDTO -> {
            List<ReportResponseDTO> reportResponseDTOList = reportResponseMapperWithMovements(initialDate, finalDate, accountResponseDTO);
            response.addAll(reportResponseDTOList);
        });
        return response;
    }

    private static List<ReportResponseDTO> reportResponseMapperWithMovements(LocalDate initialDate, LocalDate finalDate, AccountResponseDTO accountResponseDTO) {
        List<ReportResponseDTO> reportResponseDTOList = new ArrayList<>();
        accountResponseDTO.getMovementsList().forEach( movementResponseDTO -> {
            ReportResponseDTO reportResponseDTO = getReportResponseDTO(accountResponseDTO, movementResponseDTO);
            if (movementResponseDTO.getDate().isEqual(initialDate)
                    || movementResponseDTO.getDate().isEqual(finalDate)
                    || movementResponseDTO.getDate().isAfter(initialDate) && movementResponseDTO.getDate().isBefore(finalDate)) {
                reportResponseDTOList.add(reportResponseDTO);
            }
        });
        return reportResponseDTOList;
    }

    private static ReportResponseDTO getReportResponseDTO(AccountResponseDTO accountResponseDTO, MovementResponseDTO movementResponseDTO) {
        log.info("REPORT: DTO Account: {}, DTO Movement: {}", accountResponseDTO.toString(), movementResponseDTO.toString());
        ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
        reportResponseDTO.setAccountNumber(accountResponseDTO.getAccountNumber());
        reportResponseDTO.setInitialBalance(movementResponseDTO.getBalance());
        reportResponseDTO.setName(accountResponseDTO.getClient().getName());
        reportResponseDTO.setAccountType(accountResponseDTO.getAccountType());
        reportResponseDTO.setState(accountResponseDTO.getState());
        reportResponseDTO.setDate(movementResponseDTO.getDate());
        reportResponseDTO.setMovimiento(movementResponseDTO.getMovementType().equals(MovementType.DEBITO.toString()) ?
                "-" + movementResponseDTO.getValue() :
                String.valueOf(movementResponseDTO.getValue()));
        reportResponseDTO.setBalance(movementResponseDTO.getMovementType().equals(MovementType.DEBITO.toString()) ?
                movementResponseDTO.getBalance() - movementResponseDTO.getValue() :
                movementResponseDTO.getBalance() + movementResponseDTO.getValue());
        return reportResponseDTO;
    }

}
