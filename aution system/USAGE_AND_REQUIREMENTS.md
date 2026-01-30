# System Usage & Requirements Mapping

This document explains how the implemented system aligns with your original requirements and how to use it.

## 1. Requirements vs. Implementation

| Requirement | Implementation Status | Details |
| :--- | :--- | :--- |
| **Backend Stack** | ✅ Completed | Built with **Spring Boot 3.2.2** and **Java 17**. Uses Maven. |
| **Database** | ✅ Completed | Originally MySQL, **migrated to PostgreSQL** as requested. |
| **Scheduling** | ✅ Completed | `AuctionScheduler` runs every minute to close auctions automatically. |
| **Documentation** | ✅ Completed | **Swagger UI** configured for API interaction. |
| **User Entity** | ✅ Completed | `User` entity with `Role` (BIDDER, SELLER, ADMIN). BCrypt encryption used. |
| **Auction Entity** | ✅ Completed | `AuctionItem` with status and relationships. |
| **Bidding Logic** | ✅ Completed | `BidService` enforces: <br>1. Auction must be ACTIVE. <br>2. Time must be valid. <br>3. Bid > Current Highest. |
| **Concurrency** | ✅ Completed | `AuctionItem` has `@Version` for **Optimistic Locking** to prevent race conditions. |
| **Winner Logic** | ✅ Completed | Scheduler checks expired items, picks highest bid, saves to `Winner` table. |
| **Auth** | ✅ Completed | `POST /register` and `POST /login` implemented with Spring Security. |

## 2. How to Use the System (User Flow)

### Step 1: Registration
You need users to interact with the system.
*   **Action**: Use `POST /api/users/register`.
*   **Payload**:
    ```json
    {
      "name": "John Seller",
      "email": "john@example.com",
      "password": "pass",
      "role": "SELLER"
    }
    ```
*   *Note: Two users (Seller Bob & Bidder Alice) are auto-created on startup.*

### Step 2: Login
*   **Action**: Use `POST /api/users/login`.
*   **Payload**: `{"email": "john@example.com", "password": "pass"}`.
*   **Result**: Returns success. The system uses Basic Auth/Session for simplicity in Swagger.

### Step 3: Create an Auction (As Seller)
*   **Action**: Use `POST /api/auctions`.
*   **Auth**: Log in as a SELLER (or use `seller@example.com`).
*   **Payload**:
    ```json
    {
      "title": "Gaming PC",
      "description": "High-end rig",
      "basePrice": 500.00,
      "startTime": "2024-10-01T10:00:00",
      "endTime": "2024-10-01T10:05:00"
    }
    ```

### Step 4: View Auctions (Public)
*   **Action**: Use `GET /api/auctions`.
*   **Result**: Lists all items with status `ACTIVE`.

### Step 5: Place a Bid (As Bidder)
*   **Action**: Use `POST /api/bids`.
*   **Auth**: Log in as a BIDDER (or use `bidder@example.com`).
*   **Payload**:
    ```json
    {
      "itemId": 1,
      "amount": 600.00
    }
    ```
*   **Logic**: If you try to bid 400.00 (below base/current), it will fail.

### Step 6: Automated Closure (The "Brain")
*   **Action**: Wait.
*   **Logic**: Every 60 seconds, the system checks for `endTime < NOW`.
*   **Result**:
    1.  Item status changes to `CLOSED`.
    2.  Highest bidder is found.
    3.  Record created in `winners` table.
