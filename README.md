# 📈 Investment Analytics Platform

A production-inspired Java backend project demonstrating a microservices architecture for investment portfolio analytics using **Spring Boot**, **gRPC**, **Redis**, and **MySQL**.

---

# 🚀 Overview

Investment Analytics Platform is a multi-module backend application designed to manage investment portfolios and calculate financial performance.

The project follows a **microservices architecture** where responsibilities are separated into dedicated services.

- **Portfolio Service** manages portfolios, investments and cash flows.
- **Calculation Service** performs financial calculations.
- The services communicate through **gRPC** using **Protocol Buffers**.
- Portfolio performance results are cached in **Redis** to reduce unnecessary recalculations.

---

# 🏗 Architecture

```text
                     REST
               +--------------+
               |   Postman    |
               +------+-------+
                      |
                      |
                      ▼
      +--------------------------------+
      | Portfolio Service              |
      |--------------------------------|
      | Spring Boot                    |
      | REST API                       |
      | Controllers                    |
      | Services                       |
      | Spring Data JPA                |
      | Hibernate                      |
      | MySQL                          |
      | Redis Cache                    |
      +---------------+----------------+
                      |
                    gRPC
                      |
                      ▼
      +--------------------------------+
      | Calculation Service            |
      |--------------------------------|
      | Spring Boot                    |
      | gRPC Server                    |
      | Business Logic                 |
      | Financial Calculations         |
      +--------------------------------+
```

---

# ⚙ Technologies

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- MySQL
- Redis
- gRPC
- Protocol Buffers
- Maven
- Docker
- JUnit 5
- Lombok

---

# 📂 Project Structure

```text
investment-analytics-platform

├── portfolio-service
│   ├── Controllers
│   ├── Services
│   ├── Repositories
│   ├── DTOs
│   ├── Redis Cache
│   └── MySQL

├── calculation-service
│   ├── gRPC Server
│   ├── CalculationService
│   └── Unit Tests

├── common-proto
│   ├── calculation.proto
│   └── Generated gRPC Classes

├── docker-compose.yml
└── pom.xml
```

---

# ✨ Features

## Portfolio Management

- Create Portfolio
- Retrieve Portfolio

## Investment Management

- Create Investment
- Retrieve Investments

## Cash Flow Management

Supports:

- CONTRIBUTION
- DISTRIBUTION
- NAV

---

# 📊 Portfolio Performance

The Calculation Service computes:

- Total Contributions
- Total Distributions
- Current NAV
- Profit
- Simple Return

Formula:

```text
Profit = NAV + Distributions − Contributions

Simple Return = (Profit / Contributions) × 100
```

Example:

```text
Contribution : 10000

Distribution : 1500

Current NAV : 12000
```

Result:

```text
Profit = 3500

Simple Return = 35%
```

---

# ⚡ Redis Cache

Portfolio performance is cached inside Redis.

## First request

```text
GET Performance

↓

Redis

↓

CACHE MISS

↓

MySQL

↓

gRPC

↓

Calculation Service

↓

Store in Redis

↓

Return Response
```

## Second request

```text
GET Performance

↓

Redis

↓

CACHE HIT

↓

Return Cached Response
```

---

# 🗑 Cache Invalidation

Whenever a new Cash Flow is created:

```text
POST CashFlow

↓

Save to MySQL

↓

Delete Redis Cache

↓

Next GET

↓

CACHE MISS
```

Additionally, cached entries automatically expire after a configured TTL.

---

# ▶ Running the Project

## 1. Start Infrastructure

```bash
docker compose up -d
```

This starts:

- MySQL
- Redis

---

## 2. Start Calculation Service

```bash
mvn -pl calculation-service spring-boot:run
```

---

## 3. Start Portfolio Service

```bash
mvn -pl portfolio-service spring-boot:run
```

---

# 🌐 REST API

## Create Portfolio

POST

```
/api/portfolios
```

```json
{
  "name": "Tech Portfolio",
  "ownerName": "Marko Blagojevic"
}
```

---

## Create Investment

POST

```
/api/investments
```

```json
{
  "name": "Apple",
  "assetClass": "Stock",
  "portfolioId": 1
}
```

---

## Create Cash Flow

POST

```
/api/cashflows
```

```json
{
  "amount": 10000,
  "date": "2024-01-01",
  "type": "CONTRIBUTION",
  "investmentId": 1
}
```

---

## Calculate Performance

GET

```
/api/portfolios/1/performance
```

Example Response

```json
{
    "portfolioId": 1,
    "totalContributions": 10000,
    "totalDistributions": 1500,
    "currentNav": 12000,
    "profit": 3500,
    "simpleReturn": 35.0
}
```

---

# 🧪 Testing

The project contains unit tests for the financial calculation logic.

Current test coverage:

- Contribution calculation
- Distribution calculation
- NAV calculation
- Profit calculation
- Simple Return calculation

Run tests:

```bash
mvn test
```

---

# 🔮 Future Improvements

- Dockerize both microservices
- GitHub Actions CI/CD
- Integration Tests
- Authentication & Authorization
- Advanced financial metrics
    - IRR
    - Time Weighted Return
    - Sharpe Ratio

---

# 👨‍💻 Author

**Marko Blagojevic**

Computer Engineering Student

School of Computing (RAF), Belgrade

GitHub:

https://github.com/markoblagojevic