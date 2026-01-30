package com.auction.service;

import com.auction.dto.DTOs;
import com.auction.entity.AuctionItem;
import com.auction.entity.AuctionStatus;
import com.auction.entity.User;
import com.auction.repository.AuctionItemRepository;
import com.auction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private AuctionItemRepository auctionItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public AuctionItem createAuction(DTOs.AuctionRequest request, String sellerEmail) {
        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        User seller = userRepository.findByEmail(sellerEmail)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        AuctionItem item = new AuctionItem();
        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setBasePrice(request.getBasePrice());
        item.setStartTime(request.getStartTime());
        item.setEndTime(request.getEndTime());
        item.setStatus(AuctionStatus.ACTIVE); // Or SCHEDULED based on time, specifically requested ACTIVE logic by user
                                              // implies simplified usually
        // Let's do smart logic: if startTime > now, SCHEDULED, else ACTIVE.
        if (request.getStartTime().isAfter(java.time.LocalDateTime.now())) {
            item.setStatus(AuctionStatus.SCHEDULED);
        } else {
            item.setStatus(AuctionStatus.ACTIVE);
        }

        item.setSeller(seller);

        return auctionItemRepository.save(item);
    }

    public List<AuctionItem> getAllActiveAuctions() {
        return auctionItemRepository.findByStatus(AuctionStatus.ACTIVE);
    }

    public AuctionItem getAuctionById(Long id) {
        return auctionItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auction not found"));
    }
}
