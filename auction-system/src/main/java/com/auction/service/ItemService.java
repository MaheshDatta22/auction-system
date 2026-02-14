package com.auction.service;

import com.auction.model.Item;
import com.auction.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ItemService {

 private final ItemRepository repo;

 public ItemService(ItemRepository repo){this.repo=repo;}

 public List<Item> getAll(){return repo.findAll();}

 public Optional<Item> get(Long id){return repo.findById(id);}

 public Item update(Long id,Item req){
  Item i=repo.findById(id).orElseThrow();
  i.setName(req.getName());
  i.setDescription(req.getDescription());
  i.setBasePrice(req.getBasePrice());
  return repo.save(i);
 }

 public void delete(Long id){repo.deleteById(id);}

 public Item create(Item item){return repo.save(item);}
}