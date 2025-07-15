# Rushaul 🚚📦  
*A lightweight logistics management backend built with Spring Boot and PostgreSQL.*

---

## 📌 Overview
**Rushaul** is a backend system designed to handle the core logistics operations of a delivery network. It manages users, hubs, orders, and delivery assignments while ensuring structured data flow and relational integrity using Liquibase migrations.

This project is designed to be a clean, modular starting point for building more advanced logistics or e-commerce systems.

---

## 🛠️ Tech Stack
- Spring Boot (Java 21)
- PostgreSQL
- Spring Data JPA / Hibernate
- Liquibase (YAML-based changelogs)
- Postman (for API testing)
- Spring Security (JWT)

---

## 📂 Modules & Features

### 1. User Management
- Supports roles like customers and delivery personnel
- Unique username + email fields
- Secure password storage ready (hashing can be added later)

### 2. Hubs
- Stores details of logistics hubs (location, contact, coordinates)
- Ensures unique hub names for clarity

### 3. Order Management
- Orders include seller and destination info
- Generates OTPs for pickup and delivery
- Tied to customer and hubs via foreign keys

### 4. Delivery Assignments
- Assigns orders to personnel
- Tracks assignment status with timestamps

### 5. Role-based Access
- Efficient authorization
- provides access based on ADMIN/USER/DRIVER

### 6. APIs
- Protected by JWT
- Documentation via Swagger UI

---

## 🔁 API Endpoints

### Base URL: `http://localhost:8080/api/users`

**Examples:**
- `GET /api/users` — List all users  
- `POST /api/users/createUser` — Create a user  
- (Similar routes exist for hubs, orders, and assignments via their respective controllers)
- `POST /api/auth/register` — Register a new user (default: `USER` wherein USER = CUSTOMER)
- `POST /api/auth/login` — Login and receive a JWT token
- Use the token in the `Authorization: Bearer <token>` header for protected endpoints

---

## 🧱 Database Schema (via Liquibase)
- All tables (`users`, `hubs`, `orders`, `delivery_assignments`) are created via Liquibase changelogs.
- Foreign keys ensure relational integrity.
- No manual table creation needed — auto-applies on app start.

---

## 🚀 Getting Started

### Prerequisites:
- Java 21
- Maven
- PostgreSQL running locally

### Steps:
1. Clone this repo  `git clone https://github.com/your-username/rushaul.git`
2. Set up PostgreSQL and configure DB connection in `application.properties`
3. Run the application:  `mvn spring-boot:run`
4. Swagger Ui: `http://localhost:8080/swagger-ui/index.html`
5. Use Postman or browser to test endpoints (`http://localhost:8080/api/users` etc.)

---

## 📌 To Do (Future Enhancements)
- Dockerize for production setup
- Add pagination/search filters on list endpoints

---

## 📎 License
MIT License (or your choice)

---

## 👤 Author
**Anoushka Sinha**  
GitHub: [@anoushka-sinha04](https://github.com/anoushka-sinha04)

