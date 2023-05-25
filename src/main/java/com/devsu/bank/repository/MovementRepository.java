package com.devsu.bank.repository;

import com.devsu.bank.dto.MovementResponseDTO;
import com.devsu.bank.model.Movements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movements,Integer> {
    List<Movements> findAllByAccountId(Integer accountId);
}
