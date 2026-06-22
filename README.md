# 🔗 Shortify Backend

A secure and scalable URL shortening REST API built with Spring Boot, Spring Security, JWT Authentication, PostgreSQL, and Docker.

The application allows users to register, authenticate, create short URLs, manage their links, and perform redirections through a secure and production-ready backend architecture.

---

## 🚀 Features

* User Registration & Authentication
* JWT-Based Authorization
* URL Shortening Service
* Redirect to Original URL
* User-Specific URL Management
* RESTful API Design
* Password Encryption with BCrypt
* PostgreSQL Database Integration
* Docker Support
* Production Deployment Ready

---

## 🛠️ Tech Stack

### Backend

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* JWT (JSON Web Tokens)
* Maven

### Database

* PostgreSQL (Neon)

### Deployment

* Render
* Docker

---

## 📂 Project Structure

```text
src/main/java/com/urlshortener

├── config/
│   ├── SecurityConfig.java
│   ├── JwtAuthenticationFilter.java
│
├── controller/
│   ├── AuthController.java
│   ├── UrlController.java
│
├── dto/
│
├── entity/
│   ├── User.java
│   ├── ShortUrl.java
│
├── repository/
│   ├── UserRepository.java
│   ├── ShortUrlRepository.java
│
├── security/
│   ├── JwtService.java
│
├── service/
│   ├── AuthService.java
│   ├── UrlService.java
│
└── UrlShortenerApplication.java
```

---

## 🏗️ System Architecture

```text
React Frontend (Netlify)
          │
          ▼
Spring Boot REST API (Render)
          │
          ▼
PostgreSQL Database (Neon)
```

---

## 🔐 Authentication Flow

1. User registers an account.
2. Password is encrypted using BCrypt.
3. User logs in with credentials.
4. JWT token is generated.
5. Token is sent with protected requests.
6. Spring Security validates token before granting access.

---

## ⚙️ Environment Variables

Configure the following variables:

```env
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=

JWT_SECRET=

FRONTEND_URL=
```

---

## 🚀 Running Locally

### Clone Repository

```bash
git clone https://github.com/your-username/shortify-backend.git
```

### Navigate to Project

```bash
cd shortify-backend
```

### Configure Database

Update:

```properties
application.properties
```

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application will start on:

```text
http://localhost:8080
```

---

## 🐳 Docker Support

Build Docker Image:

```bash
docker build -t shortify-backend .
```

Run Container:

```bash
docker run -p 8080:8080 shortify-backend
```

---

## 📡 API Endpoints

### Authentication

| Method | Endpoint           | Description       |
| ------ | ------------------ | ----------------- |
| POST   | /api/auth/register | Register User     |
| POST   | /api/auth/login    | Authenticate User |

### URL Management

| Method | Endpoint       | Description      |
| ------ | -------------- | ---------------- |
| POST   | /api/urls      | Create Short URL |
| GET    | /api/urls      | Get User URLs    |
| DELETE | /api/urls/{id} | Delete URL       |

### Redirection

| Method | Endpoint       |
| ------ | -------------- |
| GET    | /r/{shortCode} |

---

## 📈 Future Enhancements

* Custom URL Aliases
* QR Code Generation
* Link Analytics
* URL Expiration
* Rate Limiting
* Redis Caching
* Admin Dashboard

---

## 🌍 Live Deployment

Frontend:

https://your-frontend.netlify.app

Backend:

https://your-backend.onrender.com

---

## 👨‍💻 Author

Pankaj Prajapati

Java Backend Developer

GitHub: https://github.com/dev-pankajprajapati

---


