package com.onloneshop.repository;

import com.onloneshop.domain.ProductInShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInShopRepository extends JpaRepository<ProductInShop, Integer> {
}
