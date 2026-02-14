package com.auction.service;

import com.auction.model.Item;
import com.auction.model.Bid;
import com.auction.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuctionService {

 private final ItemRepository itemRepo;
 private final BidService bidService;

 public AuctionService(ItemRepository itemRepo,BidService bidService){
  this.itemRepo=itemRepo;
  this.bidService=bidService;
 }

 public void startAuction(Long id){
  Item i=itemRepo.findById(id).orElseThrow();
  i.setAuctionActive(true);
  i.setAuctionEnded(false);
  itemRepo.save(i);
 }

 public void endAuction(Long id){
  Item i=itemRepo.findById(id).orElseThrow();
  i.setAuctionActive(false);
  i.setAuctionEnded(true);
  itemRepo.save(i);
 }

 public Optional<Bid> result(Long id){
  Item i=itemRepo.findById(id).orElseThrow();
  if(!i.isAuctionEnded()) return Optional.empty();
  return bidService.getHighestBid(id);
 }
}