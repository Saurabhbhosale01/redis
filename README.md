# Redis Application

A Spring Boot application demonstrating how to integrate **Redis caching** with **PostgreSQL** using Spring Data Redis.

The application follows a **Cache-Aside Pattern**:

1. Check Redis for the requested data.
2. If data exists in Redis → return it.
3. If data does not exist → fetch it from PostgreSQL.
4. Store the fetched data in Redis.
5. Return the data to the client.

---

## 📌 Features

- User management using Spring Boot
- PostgreSQL for persistent data storage
- Redis for caching user data
- Cache-Aside pattern
- Redis `GET` and `SET` operations
- Cache expiration using TTL
- JSON serialization/deserialization

## 🏗️ Architecture

```text
Client
  |
  v
Controller
  |
  v
Service
  |
  +---------> Redis
  |             |
  |          Cache Hit
  |             |
  |             v
  |          Response
  |
  +---------> PostgreSQL
                |
             Cache Miss
                |
                v
              Redis

## Tech Stack

- Java 21
- Spring Boot 4.1.1
- Spring Web
- Spring Data JPA
- Spring Data Redis
- PostgreSQL
- Redis
- Maven
- Jackson
- ModelMapper

---

## Project Architecture

```text
                    Client
                      |
                      v
               User Controller
                      |
                      v
                User Service
                      |
              +-------+-------+
              |               |
              v               v
           Redis          PostgreSQL
           Cache          Database
              |
              v
        Cached User Data
