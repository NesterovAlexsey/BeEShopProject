package com.onloneshop.repository;

import com.onloneshop.domain.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shops, Integer> {
}
