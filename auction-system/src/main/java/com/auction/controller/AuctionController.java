package com.auction.controller;

import com.auction.model.Bid;
import com.auction.service.AuctionService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

 private final AuctionService service;

 public AuctionController(AuctionService s){this.service=s;}

 @PostMapping("/{itemId}/start")
 public void start(@PathVariable Long itemId){service.startAuction(itemId);}

 @PutMapping("/{itemId}/end")
 public void end(@PathVariable Long itemId){service.endAuction(itemId);}

 @GetMapping("/{itemId}/result")
 public Optional<Bid> result(@PathVariable Long itemId){
  return service.result(itemId);
 }
}