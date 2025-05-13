# Sports Cart User Service

This is a Spring Boot backend service that provides JWT-based authentication for users of the Sports Cart platform. It includes a login endpoint and protects all other endpoints using bearer tokens.

---

## Features

- JWT authentication with Spring Security
- Custom user login using email and password
- BCrypt password hashing
- Stateless session management
- `/api/auth/login` endpoint to get JWT tokens
