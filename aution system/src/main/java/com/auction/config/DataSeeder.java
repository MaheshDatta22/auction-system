package com.auction.config;

import com.auction.entity.AuctionItem;
import com.auction.entity.AuctionStatus;
import com.auction.entity.Role;
import com.auction.entity.User;
import com.auction.repository.AuctionItemRepository;
import com.auction.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, AuctionItemRepository auctionItemRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User seller = new User();
                seller.setName("Seller Bob");
                seller.setEmail("seller@example.com");
                seller.setPassword(passwordEncoder.encode("password"));
                seller.setRole(Role.SELLER);
                userRepository.save(seller);

                User bidder = new User();
                bidder.setName("Bidder Alice");
                bidder.setEmail("bidder@example.com");
                bidder.setPassword(passwordEncoder.encode("password"));
                bidder.setRole(Role.BIDDER);
                userRepository.save(bidder);

                AuctionItem item1 = new AuctionItem();
                item1.setTitle("Vintage Laptop");
                item1.setDescription("A very old laptop.");
                item1.setBasePrice(new BigDecimal("100.00"));
                item1.setStartTime(LocalDateTime.now().minusHours(1));
                item1.setEndTime(LocalDateTime.now().plusDays(1));
                item1.setStatus(AuctionStatus.ACTIVE);
                item1.setSeller(seller);
                auctionItemRepository.save(item1);

                AuctionItem item2 = new AuctionItem();
                item2.setTitle("Rare Coin");
                item2.setDescription("Gold coin from 1800s.");
                item2.setBasePrice(new BigDecimal("500.00"));
                item2.setStartTime(LocalDateTime.now().minusHours(2));
                item2.setEndTime(LocalDateTime.now().plusHours(2));
                item2.setStatus(AuctionStatus.ACTIVE);
                item2.setSeller(seller);
                auctionItemRepository.save(item2);

                System.out.println("Data Seeding Completed.");
            }
        };
    }
}
