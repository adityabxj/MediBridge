# MediBridge

**MediBridge** is a full-stack, containerized healthcare management platform built with a microservices architecture. It provides secure, scalable, and modular services for managing patients, doctors, appointments, health records, and billing workflows. The platform includes a responsive Angular frontend and a robust Java-based backend powered by Spring Boot and Kafka.

---

## 🚀 Tech Stack

### 🧠 Backend (Microservices)
- **Java 24**, **Spring Boot**, **Maven**
- **gRPC** – Fast inter-service communication
- **Apache Kafka** – Event-driven architecture
- **PostgreSQL** – Persistent data store (containerized)
- **Swagger/OpenAPI** – API documentation
- **Docker** – Containerization of services

### 🌐 Microservices
- `api-gateway` – Central entry point for all client requests
- `auth-service` – JWT-based authentication and authorization
- `patient-service` – Handles appointments, patients, doctors, test packages, and health records
- `billing-service` – Manages billing and payments
- `analytics-service` – Provides statistical and analytical data

### 💻 Frontend
- **Angular**
- **Bootstrap**, **Tailwind CSS**
- **TypeScript**

---

## 🏗️ Features

- 📅 **Appointments**: Patients can book appointments with doctors
- 👨‍⚕️ **Health Records**: Doctors can create and manage patient records
- 💳 **Billing**: Patients can purchase health packages and view billing information
- 📊 **Analytics**: Insightful dashboards and service statistics
- 🔒 **Authentication**: Secure JWT-based login and role-based access control
- ⚙️ **gRPC + Kafka**: Efficient communication and asynchronous processing
- 🐳 **Dockerized Setup**: Fully containerized services, Kafka, and PostgreSQL for easy deployment

---

## 📦 Project Structure

```bash
medibridge/
├── api-gateway/
├── auth-service/
├── patient-service/
├── billing-service/
├── analytics-service/
├── frontend/              # Angular app
├── docker-compose.yml     # Multi-service orchestration
├── README.md
