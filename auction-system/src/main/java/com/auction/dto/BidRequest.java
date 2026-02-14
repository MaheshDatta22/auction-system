package com.auction.dto;
public class BidRequest {
 private Long userId;
 private Long itemId;
 private Double amount;

 public Long getUserId(){return userId;}
 public void setUserId(Long u){this.userId=u;}
 public Long getItemId(){return itemId;}
 public void setItemId(Long i){this.itemId=i;}
 public Double getAmount(){return amount;}
 public void setAmount(Double a){this.amount=a;}
}