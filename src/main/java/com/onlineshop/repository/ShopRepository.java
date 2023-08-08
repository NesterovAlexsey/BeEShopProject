package com.onlineshop.repository;

import com.onlineshop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    List<Shop> findByShopNameIgnoreCase(String shopName);
}
