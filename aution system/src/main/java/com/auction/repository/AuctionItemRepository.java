package com.auction.repository;

import com.auction.entity.AuctionItem;
import com.auction.entity.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
    List<AuctionItem> findByStatus(AuctionStatus status);
}
