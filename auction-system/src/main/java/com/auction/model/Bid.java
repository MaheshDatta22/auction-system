package com.auction.model;

import jakarta.persistence.*;

@Entity
public class Bid {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private Double amount;
 private Long userId;
 private Long itemId;

 public Long getId(){return id;}
 public Double getAmount(){return amount;}
 public void setAmount(Double amount){this.amount=amount;}
 public Long getUserId(){return userId;}
 public void setUserId(Long userId){this.userId=userId;}
 public Long getItemId(){return itemId;}
 public void setItemId(Long itemId){this.itemId=itemId;}
}