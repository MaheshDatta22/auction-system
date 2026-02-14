package com.auction.controller;

import com.auction.dto.*;
import com.auction.model.Bid;
import com.auction.service.BidService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bids")
public class BidController {

 private final BidService service;

 public BidController(BidService service){this.service=service;}

 @PostMapping
 public Bid place(@RequestBody BidRequest r){
  return service.placeBid(r.getUserId(),r.getItemId(),r.getAmount());
 }

 @PutMapping("/{id}")
 public Bid update(@PathVariable Long id,@RequestBody UpdateBidRequest r){
  return service.updateBid(id,r.getAmount());
 }

 @DeleteMapping("/{id}")
 public void delete(@PathVariable Long id){
  service.deleteBid(id);
 }
}