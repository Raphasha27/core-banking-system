package com.banking.corebankingsystem.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "ledger_entries")
public class LedgerEntry {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String accountId;
    private String entryType;
    private BigDecimal amount;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private String reference;
    private String description;
    private LocalDateTime createdAt;

    public LedgerEntry() {}
    public LedgerEntry(String accountId, String entryType, BigDecimal amount,
                       BigDecimal balanceBefore, BigDecimal balanceAfter, String reference, String description) {
        this.accountId = accountId; this.entryType = entryType; this.amount = amount;
        this.balanceBefore = balanceBefore; this.balanceAfter = balanceAfter;
        this.reference = reference; this.description = description;
        this.createdAt = LocalDateTime.now();
    }
    public String getId() { return id; }
    public String getAccountId() { return accountId; }
    public String getEntryType() { return entryType; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getBalanceBefore() { return balanceBefore; }
    public BigDecimal getBalanceAfter() { return balanceAfter; }
    public String getReference() { return reference; }
    public String getDescription() { return description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
