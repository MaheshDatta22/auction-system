package com.auction.service;

import com.auction.dto.DTOs;
import com.auction.entity.AuctionItem;
import com.auction.entity.AuctionStatus;
import com.auction.entity.Bid;
import com.auction.entity.User;
import com.auction.repository.AuctionItemRepository;
import com.auction.repository.BidRepository;
import com.auction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionItemRepository auctionItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Bid placeBid(DTOs.BidRequest request, String bidderEmail) {
        User bidder = userRepository.findByEmail(bidderEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AuctionItem item = auctionItemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Auction item not found"));

        // 1. Check Status
        if (item.getStatus() != AuctionStatus.ACTIVE) {
            throw new RuntimeException("Auction is not active");
        }

        // 2. Check Time
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(item.getStartTime()) || now.isAfter(item.getEndTime())) {
            throw new RuntimeException("Bidding is not allowed at this time");
        }

        // 3. Check Amount > Highest Bid
        BigDecimal currentHighest = bidRepository.findHighestBidAmount(item.getItemId());
        if (currentHighest == null) {
            currentHighest = item.getBasePrice();
        }

        if (request.getAmount().compareTo(currentHighest) <= 0) {
            throw new RuntimeException("Bid amount must be higher than current highest bid: " + currentHighest);
        }

        Bid bid = new Bid();
        bid.setAuctionItem(item);
        bid.setBidder(bidder);
        bid.setBidAmount(request.getAmount());

        return bidRepository.save(bid);
    }
}
