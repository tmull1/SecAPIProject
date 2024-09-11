# SecAPI Application

## Overview

This Spring Boot application demonstrates enhanced networking and security aspects, including HTTPS configuration, CORS, CSRF protection, input validation, Redis caching, and more. The application provides basic user registration, login, and token generation functionalities. It can be tested in Postman.

## Features

- **HTTPS Configuration**: The application is configured to use HTTPS with a self-signed SSL certificate.
- **CORS**: Allows cross-origin requests from specific domains.
- **CSRF Protection**: Implements CSRF tokens for security.
- **Input Validation**: User input is validated using Jakarta validation annotations.
- **Redis Caching**: User details are cached in Redis for improved performance.
- **Role-Based Access Control**: Protects specific endpoints with role-based security (e.g., `ADMIN` role).

---

## Prerequisites

- Java 17+
- Maven 3.6+
- Redis server running locally or remotely
- Postman (or any other API testing tool)
- HTTPS certificate (self-signed or CA-signed)