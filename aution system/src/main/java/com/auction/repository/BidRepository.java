package com.auction.repository;

import com.auction.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.Optional;

public interface BidRepository extends JpaRepository<Bid, Long> {
    @Query("SELECT MAX(b.bidAmount) FROM Bid b WHERE b.auctionItem.itemId = :itemId")
    BigDecimal findHighestBidAmount(@Param("itemId") Long itemId);

    @Query("SELECT b FROM Bid b WHERE b.auctionItem.itemId = :itemId ORDER BY b.bidAmount DESC LIMIT 1")
    Optional<Bid> findHighestBid(@Param("itemId") Long itemId);
}
