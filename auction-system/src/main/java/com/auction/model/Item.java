package com.auction.model;

import jakarta.persistence.*;

@Entity
public class Item {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 private String description;
 private double basePrice;
 private boolean auctionActive=false;
 private boolean auctionEnded=false;

 public Long getId(){return id;}
 public String getName(){return name;}
 public void setName(String name){this.name=name;}
 public String getDescription(){return description;}
 public void setDescription(String d){this.description=d;}
 public double getBasePrice(){return basePrice;}
 public void setBasePrice(double p){this.basePrice=p;}
 public boolean isAuctionActive(){return auctionActive;}
 public void setAuctionActive(boolean a){this.auctionActive=a;}
 public boolean isAuctionEnded(){return auctionEnded;}
 public void setAuctionEnded(boolean e){this.auctionEnded=e;}
}