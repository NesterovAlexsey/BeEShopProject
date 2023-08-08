package com.onlineshop.service;

import com.onlineshop.controller.dto.ShopDTO;
import com.onlineshop.domain.Shop;
import com.onlineshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    //tODO add loging
    public List<ShopDTO> findAllShops() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> shopDTOList = new ArrayList<>();
        shops.forEach(shop -> shopDTOList.add(ShopDTO.getInstance(shop)));
        return shopDTOList;
    }

    //Todo - check Shop name for same in adding
    public ShopDTO addShop(ShopDTO shopDTO) {
        Shop addShop = new Shop();
        addShop.setShopName(shopDTO.getShopName());
        addShop = shopRepository.save(addShop);
        return ShopDTO.getInstance(addShop);
    }

    //Todo - check Shop name for same in update
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
