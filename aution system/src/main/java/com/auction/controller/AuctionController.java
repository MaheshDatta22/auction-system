package com.auction.controller;

import com.auction.dto.DTOs;
import com.auction.entity.AuctionItem;
import com.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping
    public ResponseEntity<?> createAuction(@RequestBody DTOs.AuctionRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        try {
            AuctionItem item = auctionService.createAuction(request, email);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AuctionItem>> getActiveAuctions() {
        return ResponseEntity.ok(auctionService.getAllActiveAuctions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuction(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(auctionService.getAuctionById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
