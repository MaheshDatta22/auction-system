# How to Run the Online Auction System

Follow these steps to get the application running on your local machine.

## 1. Prerequisites
- **Java 17** or higher installed (`java -version`)
- **Maven** installed (`mvn -version`)
- **PostgreSQL** installed and running on port `5432`

## 2. Database Setup
1. Open your PostgreSQL tool (pgAdmin, DBeaver, or command line).
2. Create a new database named `auction_db`.
   ```sql
   CREATE DATABASE auction_db;
   ```
   *Note: You do not need to create tables. The application will create them automatically.*

## 3. Configuration Check
You have already updated `src/main/resources/application.properties`. verification:
- URL: `jdbc:postgresql://localhost:5432/auction_db`
- Username: `postgres`
- Password: `rahul` (As you updated)

## 4. Build and Run
Open your terminal in the project root folder (`c:\Users\RAHUL SUNKAVALLI\Downloads\aution system`) and run:

1. **Clean and Build**:
   ```bash
   mvn clean install
   ```
   *Wait for "BUILD SUCCESS"*

2. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   *Wait for logs to settle. You should see "Started AuctionSystemApplication..."*

## 5. Usage
Once running, access the system here:

- **Swagger UI (API Docs & Testing)**:
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Sample Login Data (Seeded automatically)
- **Seller**: `seller@example.com` / `password`
- **Bidder**: `bidder@example.com` / `password`
