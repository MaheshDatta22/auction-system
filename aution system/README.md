# Online Auction System

A robust Online Auction System built with Spring Boot, MySQL, and Scheduler.

## Tech Stack
- **Backend**: Spring Boot 3.2.2 (Java 17)
- **Database**: PostgreSQL
- **Security**: Spring Security (BCrypt)
- **Docs**: OpenAPI / Swagger

## Setup & Running

1.  **Database**:
    - Ensure PostgreSQL is running on port 5432.
    - Create database `auction_db`.
    - Update credentials in `src/main/resources/application.properties` (Default: `postgres` / `password`).

2.  **Build & Run**:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3.  **Access**:
    - **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    - **API Base**: `http://localhost:8080/api`

## Default Users (Seeded)
- **Seller**: `seller@example.com` / `password`
- **Bidder**: `bidder@example.com` / `password`

## API Endpoints

### Auth
- `POST /api/users/register`: Register new user.
- `POST /api/users/login`: Login (returns success message, uses Basic Auth/Session).

### Auctions
- `POST /api/auctions`: Create auction (Seller only).
- `GET /api/auctions`: View active auctions (Public).

### Bids
- `POST /api/bids`: Place a bid (Bidder only).
    - Body: `{"itemId": 1, "amount": 150.00}`

## Scheduling
- The system checks for expired auctions every minute.
- Winners are automatically declared and saved to the `winners` table.
