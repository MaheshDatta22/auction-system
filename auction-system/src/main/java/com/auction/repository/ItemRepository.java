package com.auction.repository;
import com.auction.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ItemRepository extends JpaRepository<Item,Long>{}