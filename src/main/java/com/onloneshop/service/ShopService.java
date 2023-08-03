package com.onloneshop.service;

import com.onloneshop.controller.dto.ShopDTO;
import com.onloneshop.domain.Shop;
import com.onloneshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public List<ShopDTO> findAllShops() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> shopDTOList = new ArrayList<>();
        shops.forEach(shop -> shopDTOList.add(ShopDTO.getInstance(shop)));
        return shopDTOList;
    }

    public ShopDTO addShop(ShopDTO shopDTO) {
        Shop addShop = new Shop();
        addShop.setShopName(shopDTO.getShopName());
        addShop = shopRepository.save(addShop);
        return ShopDTO.getInstance(addShop);
    }

    public ShopDTO update(Integer id, ShopDTO shopDTO) {
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isPresent()) {
            Shop updshop = shop.get();
            updshop.setShopName(shopDTO.getShopName());
            updshop = shopRepository.save(updshop);
            return ShopDTO.getInstance(updshop);
        }
        return null;
    }

    public ShopDTO deleteShop(Integer id) {
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isPresent()) {
            Shop delShop = shop.get();
            shopRepository.delete(delShop);
            return ShopDTO.getInstance(delShop);
        }
        return null;
    }
}
