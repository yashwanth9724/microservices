# Payment Service - Spring Boot Microservice

## 📌 Overview

Payment Service is a Spring Boot based microservice that handles payment processing with idempotency support.

It ensures duplicate payment requests are safely handled using an idempotency key.

---

## 🏗️ Tech Stack

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL
- Docker
- Docker Compose
- Hibernate
- JWT Authentication
- Gradle

---

## 🔐 Features

- Create Payment
- Fetch Payment by ID
- Idempotency Key Handling
- JWT Authentication
- Dockerized Deployment
- PostgreSQL Integration

---

## 📂 Project Structure

```
microservices/
 ├── src/
 ├── Dockerfile
 ├── docker-compose.yml
 ├── build.gradle
 ├── README.md
```

---

## 🚀 Running Locally (Without Docker)

1. Install PostgreSQL locally
2. Update `application.yml`
3. Run:

```
./gradlew bootRun
```

---

## 🐳 Running With Docker (Single Container)

Build image:

```
docker build -t payment-service .
```

Run container:

```
docker run -p 8080:8080 payment-service
```

---

## 🐳 Running With Docker Compose (Recommended)

Start services:

```
docker-compose up --build
```

Services started:
- payment-service (8080)
- postgres (5432)

---

## 🧪 Sample API

Create Payment:

```
POST /api/v1/payments
```

Get Payment:

```
GET /api/v1/payments/{id}
```

---

## 📈 Future Enhancements

- Add Order Service
- Implement Saga Pattern
- Add API Gateway
- Deploy to AWS ECS
- Add GitHub Actions CI/CD
- Integrate with AWS RDS

---
