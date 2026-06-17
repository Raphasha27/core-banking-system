package com.banking.corebankingsystem.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "accounts")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String customerId;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance = BigDecimal.ZERO;
    private String currency;
    private String status = "ACTIVE";
    private LocalDateTime createdAt;

    public Account() {}
    public Account(String customerId, String accountNumber, String accountType, String currency) {
        this.customerId = customerId; this.accountNumber = accountNumber;
        this.accountType = accountType; this.currency = currency;
        this.createdAt = LocalDateTime.now();
    }
    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public BigDecimal getBalance() { return balance; }
    public String getCurrency() { return currency; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setBalance(BigDecimal b) { this.balance = b; }
    public void setStatus(String s) { this.status = s; }
}
