package com.auction.dto;

import lombok.Data;

public class DTOs {
    @Data
    public static class RegisterRequest {
        private String name;
        private String email;
        private String password;
        private String role; // BIDDER, SELLER
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    public static class AuctionRequest {
        private String title;
        private String description;
        private java.math.BigDecimal basePrice;
        private java.time.LocalDateTime startTime;
        private java.time.LocalDateTime endTime;
    }

    @Data
    public static class BidRequest {
        private Long itemId;
        private java.math.BigDecimal amount;
    }
}
