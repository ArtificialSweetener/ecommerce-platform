# E-commerce Platform (Modular Monolith)

## 📌 Project Overview

This project is a backend e-commerce platform built as a **modular monolith** with a strong focus on
clean architecture, scalability, and future migration to microservices.

The goal of this project is to practice and demonstrate **production-grade backend development**
using the Spring ecosystem.

---

## 🏗 Architecture

- Modular Monolith
- Clean Architecture (Domain / Application / Infrastructure separation)
- Event-driven mindset (future-ready for Kafka)
- Designed for easy extraction into microservices

---

## 🧰 Tech Stack

### Core
- **Java:** 17
- **Spring Boot:** 3.x
- **Build Tool:** Gradle
- **Packaging:** Jar

### Planned (next milestones)
- PostgreSQL (Flyway, JPA/Hibernate)
- Redis
- Spring Security (JWT, OAuth2)
- Kafka
- Docker & Docker Compose
- Testcontainers
- Observability (Micrometer, Actuator)

---

## ▶️ Running the application locally

### Prerequisites
- Java 17+
- Gradle (or use Gradle Wrapper)

### Start application
```bash
./gradlew bootRun
