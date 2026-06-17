package com.banking.corebankingsystem.controller;

import com.banking.corebankingsystem.model.*;
import com.banking.corebankingsystem.service.CoreBankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BankingController {
    private final CoreBankingService service;

    public BankingController(CoreBankingService service) { this.service = service; }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Map<String, String> req) {
        return ResponseEntity.ok(service.createAccount(
            req.get("customerId"), req.get("accountNumber"),
            req.get("accountType"), req.get("currency")));
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id) {
        return ResponseEntity.ok(service.getAccount(id));
    }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<List<Account>> getCustomerAccounts(@PathVariable String customerId) {
        return ResponseEntity.ok(service.getCustomerAccounts(customerId));
    }

    @PostMapping("/accounts/{id}/deposit")
    public ResponseEntity<LedgerEntry> deposit(@PathVariable String id, @RequestBody Map<String, Object> req) {
        return ResponseEntity.ok(service.deposit(id,
            new BigDecimal(req.get("amount").toString()),
            (String)req.get("reference"), (String)req.get("description")));
    }

    @PostMapping("/accounts/{id}/withdraw")
    public ResponseEntity<LedgerEntry> withdraw(@PathVariable String id, @RequestBody Map<String, Object> req) {
        return ResponseEntity.ok(service.withdraw(id,
            new BigDecimal(req.get("amount").toString()),
            (String)req.get("reference"), (String)req.get("description")));
    }

    @PostMapping("/transfers")
    public ResponseEntity<Map<String, String>> transfer(@RequestBody Map<String, Object> req) {
        service.transfer((String)req.get("fromAccountId"), (String)req.get("toAccountId"),
            new BigDecimal(req.get("amount").toString()), (String)req.get("reference"));
        return ResponseEntity.ok(Map.of("status", "COMPLETED"));
    }

    @GetMapping("/accounts/{id}/ledger")
    public ResponseEntity<List<LedgerEntry>> getLedger(@PathVariable String id) {
        return ResponseEntity.ok(service.getAccountLedger(id));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "service", "core-banking-system"));
    }
}
