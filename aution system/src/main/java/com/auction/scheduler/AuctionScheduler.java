package com.auction.scheduler;

import com.auction.entity.AuctionItem;
import com.auction.entity.AuctionStatus;
import com.auction.entity.Bid;
import com.auction.entity.Winner;
import com.auction.repository.AuctionItemRepository;
import com.auction.repository.BidRepository;
import com.auction.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class AuctionScheduler {

    @Autowired
    private AuctionItemRepository auctionItemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    @Scheduled(fixedRate = 60000) // Run every minute
    @Transactional
    public void closeExpiredAuctions() {
        List<AuctionItem> activeAuctions = auctionItemRepository.findByStatus(AuctionStatus.ACTIVE);

        for (AuctionItem item : activeAuctions) {
            if (item.getEndTime().isBefore(LocalDateTime.now())) {
                item.setStatus(AuctionStatus.CLOSED);
                auctionItemRepository.save(item);

                Optional<Bid> highestBid = bidRepository.findHighestBid(item.getItemId());

                if (highestBid.isPresent()) {
                    Bid winningBid = highestBid.get();
                    Winner winner = new Winner();
                    winner.setAuctionItem(item);
                    winner.setUser(winningBid.getBidder());
                    winner.setWinningAmount(winningBid.getBidAmount());
                    winnerRepository.save(winner);
                }
            }
        }
    }
}
