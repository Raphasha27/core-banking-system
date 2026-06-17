# Core Banking System

[![CI](https://github.com/koketseraphasha/core-banking-system/actions/workflows/ci.yml/badge.svg)](https://github.com/koketseraphasha/core-banking-system/actions/workflows/ci.yml)

Core banking operations platform with customer management, deposits, withdrawals, transfers, account statements, and branch management.

## Modules
- Customer Management
- Deposit and Withdrawal Processing
- Fund Transfers
- Account Statements
- Branch Management
- Audit Logging

## Stack
Java 21, Spring Boot, Spring Security, PostgreSQL, Docker

## Quick Start
```bash
docker compose up -d
```

## Deployment & Architecture

This project is designed with cloud-ready principles:

- **Containerized** using Docker for consistent deployment
- **Environment-based configuration** — no hardcoded secrets
- **Modular structure** for independent scaling
- **Stateless design** where applicable
- **Separation of concerns** for maintainability

### Run Locally

`ash
docker-compose up --build
`

---

*Part of the Kirov Dynamics Technology portfolio — backend engineering focused on security, scalability, and system design.*
