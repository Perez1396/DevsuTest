package com.devsu.bank.mapper;

import com.devsu.bank.dto.AccountResponseDTO;
import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.dto.ReportResponseDTO;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportMapper {

    public static List<ReportResponseDTO> mapFromAccountResponseToReport(List<AccountResponseDTO> accountResponseDTOList, List<String> dateRange){
        List<ReportResponseDTO> reportResponseDTOList = new ArrayList<>();

        accountResponseDTOList.forEach(accountResponseDTO -> {
            ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
            reportResponseDTO.setAccountNumber(accountResponseDTO.getAccountNumber());
            reportResponseDTO.setInitialBalance(accountResponseDTO.getInitialBalance());
            reportResponseDTO.setName(accountResponseDTO.getClient().getName());
            reportResponseDTO.setAccountType(accountResponseDTO.getAccountType());
            reportResponseDTO.setState(accountResponseDTO.getState());
            mapMovementsWithDateRange(reportResponseDTO, accountResponseDTO.getMovementsList(), dateRange);
            reportResponseDTOList.add(reportResponseDTO);
        });

        return reportResponseDTOList;
    }

    private static void mapMovementsWithDateRange(ReportResponseDTO reportResponseDTO, List<MovementResponseDTO> movementResponseDTOList, List<String> dateRange){

        if (!dateRange.isEmpty()){
            LocalDate initialDate = LocalDate.parse(dateRange.get(0));
            LocalDate finalDate = LocalDate.parse(dateRange.get(1));
            for (MovementResponseDTO movementResponseDTO : movementResponseDTOList) {
                if (movementResponseDTO.getDate().isEqual(initialDate)){
                    reportResponseDTO.setDate(movementResponseDTO.getDate());
                    reportResponseDTO.setMovimiento(String.valueOf(movementResponseDTO.getValue()));
                    reportResponseDTO.setBalance(movementResponseDTO.getBalance());
                }
                if (movementResponseDTO.getDate().isAfter(initialDate) && movementResponseDTO.getDate().isBefore(finalDate)) {
                    reportResponseDTO.setDate(movementResponseDTO.getDate());
                    reportResponseDTO.setMovimiento(String.valueOf(movementResponseDTO.getValue()));
                    reportResponseDTO.setBalance(movementResponseDTO.getBalance());
                }
            }
        }
    }
}
