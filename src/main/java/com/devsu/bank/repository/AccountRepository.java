package com.devsu.bank.repository;

import com.devsu.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    /**
     *
     * @param clientId
     * @return
     */
    List<Account> findAllByClientId(Integer clientId);
}
