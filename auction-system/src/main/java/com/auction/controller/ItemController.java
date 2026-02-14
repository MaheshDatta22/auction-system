package com.auction.controller;

import com.auction.model.Item;
import com.auction.model.Bid;
import com.auction.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/items")
public class ItemController {

 private final ItemService itemService;
 private final BidService bidService;

 public ItemController(ItemService i,BidService b){
  this.itemService=i;
  this.bidService=b;
 }

 @GetMapping
 public List<Item> all(){return itemService.getAll();}

 @GetMapping("/{id}")
 public Item one(@PathVariable Long id){return itemService.get(id).orElseThrow();}

 @PutMapping("/{id}")
 public Item update(@PathVariable Long id,@RequestBody Item req){
  return itemService.update(id,req);
 }

 @DeleteMapping("/{id}")
 public void delete(@PathVariable Long id){
  itemService.delete(id);
 }

 @GetMapping("/{id}/highest-bid")
 public Optional<Bid> highest(@PathVariable Long id){
  return bidService.getHighestBid(id);
 }

 @GetMapping("/{id}/bids")
 public List<Bid> bids(@PathVariable Long id){
  return bidService.getBids(id);
 }
}