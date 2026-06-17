package com.banking.corebankingsystem.repository;

import com.banking.corebankingsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByCustomerId(String customerId);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByStatus(String status);
}
