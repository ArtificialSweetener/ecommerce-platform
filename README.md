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

- **Language:** Java 25 (LTS)
- **Framework:** Spring Boot 4.0.1
- **Build Tool:** Gradle (Groovy DSL) 9.2.1
- **Database:** PostgreSQL 18.01
- **Database Migrations:** Liquibase 5.0.1

---

## 📂 Project Structure

```text
├── app/               # Spring Boot Application (Web, Services, JPA)
├── db/                # Independent Database Migration Module
│   └── src/main/resources/db/changelog/
│       ├── db.changelog-master.xml  # Root Changelog
│       ├── user/                    # User Domain Schemas
│       ├── product/                 # Product Domain Schemas
│       └── order/                   # Order Domain Schemas
├── build.gradle       # Root Project Configuration
└── settings.gradle    # Module Definitions
```

---

## ▶️ Running the application locally

### Prerequisites

- JDK 25 installed.
- Gradle (or use Gradle Wrapper)
- Docker installed (for PostgreSQL).

### ⚙️ Environment Variables
The project uses the following variables. Create a .env file in the root and set those env variables as you wish

```text
DB_NAME: Database name
DB_HOST: Database host
DB_USER: Database username
DB_PASS: Database password
```

## ▶️ Quick Start
### 1. Create the .env file to set env variables

### 2. Docker is used to manage the database and Liquibase. This avoids Java/Gradle version conflicts on your local machine.
```bash
docker-compose up -d
```

### 3. Run the application

```bash
./gradlew bootRun
