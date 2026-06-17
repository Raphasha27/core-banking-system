package com.banking.corebankingsystem.repository;

import com.banking.corebankingsystem.model.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, String> {
    List<LedgerEntry> findByAccountIdOrderByCreatedAtDesc(String accountId);
}
