package com.banking.corebankingsystem.service;

import com.banking.corebankingsystem.model.*;
import com.banking.corebankingsystem.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CoreBankingService {
    private final AccountRepository accountRepo;
    private final LedgerEntryRepository ledgerRepo;

    public CoreBankingService(AccountRepository ar, LedgerEntryRepository lr) {
        this.accountRepo = ar; this.ledgerRepo = lr;
    }

    public Account createAccount(String customerId, String accountNumber, String accountType, String currency) {
        return accountRepo.save(new Account(customerId, accountNumber, accountType, currency));
    }

    @Transactional
    public LedgerEntry deposit(String accountId, BigDecimal amount, String reference, String description) {
        Account account = accountRepo.findById(accountId).orElseThrow();
        BigDecimal before = account.getBalance();
        account.setBalance(before.add(amount));
        accountRepo.save(account);
        LedgerEntry entry = new LedgerEntry(accountId, "CREDIT", amount, before, account.getBalance(), reference, description);
        return ledgerRepo.save(entry);
    }

    @Transactional
    public LedgerEntry withdraw(String accountId, BigDecimal amount, String reference, String description) {
        Account account = accountRepo.findById(accountId).orElseThrow();
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        BigDecimal before = account.getBalance();
        account.setBalance(before.subtract(amount));
        accountRepo.save(account);
        LedgerEntry entry = new LedgerEntry(accountId, "DEBIT", amount, before, account.getBalance(), reference, description);
        return ledgerRepo.save(entry);
    }

    @Transactional
    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount, String reference) {
        withdraw(fromAccountId, amount, reference, "Transfer to " + toAccountId);
        deposit(toAccountId, amount, reference, "Transfer from " + fromAccountId);
    }

    public Account getAccount(String id) { return accountRepo.findById(id).orElseThrow(); }

    public List<Account> getCustomerAccounts(String customerId) {
        return accountRepo.findByCustomerId(customerId);
    }

    public List<LedgerEntry> getAccountLedger(String accountId) {
        return ledgerRepo.findByAccountIdOrderByCreatedAtDesc(accountId);
    }
}
