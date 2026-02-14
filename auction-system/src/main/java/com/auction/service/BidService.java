package com.auction.service;

import com.auction.model.Bid;
import com.auction.repository.BidRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BidService {

 private final BidRepository repo;

 public BidService(BidRepository repo){this.repo=repo;}

 public Bid placeBid(Long userId,Long itemId,Double amount){
  Bid b=new Bid();
  b.setUserId(userId);
  b.setItemId(itemId);
  b.setAmount(amount);
  return repo.save(b);
 }

 public List<Bid> getBids(Long itemId){
  return repo.findByItemId(itemId);
 }

 public Optional<Bid> getHighestBid(Long itemId){
  return repo.findByItemId(itemId).stream()
          .max(Comparator.comparing(Bid::getAmount));
 }

 public Bid updateBid(Long id,Double amount){
  Bid b=repo.findById(id).orElseThrow();
  b.setAmount(amount);
  return repo.save(b);
 }

 public void deleteBid(Long id){
  repo.deleteById(id);
 }
}