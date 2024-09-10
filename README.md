# SecAPI - Spring Boot Security API

## Overview

SecAPI is a Spring Boot-based REST API that implements JWT-based authentication and authorization. The application secures its endpoints using Spring Security and provides role-based access control (RBAC) for `USER` and `ADMIN` roles.

## Features

- **JWT Authentication**: Secure API endpoints using JWT tokens.
- **Role-based Access Control**: Separate permissions for `USER` and `ADMIN` roles.
- **User Registration**: Register users with username, password, and roles.
- **Login**: Obtain a JWT token by providing valid credentials.

## Technologies Used

- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
- Maven
- Java 17

## Endpoints

- **POST /api/register**: Register a new user with `username`, `password`, and roles (`USER`, `ADMIN`).
- **POST /api/login**: Authenticate a user and obtain a JWT token.
- **GET /details**: Accessible by `USER` and `ADMIN`.
- **GET /admin/details**: Accessible only by `ADMIN`.