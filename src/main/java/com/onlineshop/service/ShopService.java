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

    //Todo add loging
    public List<ShopDTO> findAllShops() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> shopDTOList = new ArrayList<>();
        shops.forEach(shop -> shopDTOList.add(ShopDTO.getInstance(shop)));
        return shopDTOList;
    }

    public ShopDTO addShop(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository.findByShopNameIgnoreCase(shopDTO.getShopName());
        if (!shops.isEmpty()) {
            return null;
        }

        Shop addShop = new Shop();
        addShop.setShopName(shopDTO.getShopName());
        addShop = shopRepository.save(addShop);
        return ShopDTO.getInstance(addShop);
    }

    public ShopServiceResponse update(Integer id, ShopDTO shopDTO) {
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isEmpty()) {
            return new ShopServiceResponse(shopDTO, 404);
        }

        List<Shop> shops = shopRepository.findByShopNameIgnoreCase(shopDTO.getShopName());
        if (!shops.isEmpty()) {
            return new ShopServiceResponse(shopDTO, 400);
        }

        Shop updshop = shop.get();
        updshop.setShopName(shopDTO.getShopName());
        updshop = shopRepository.save(updshop);
        return new ShopServiceResponse(ShopDTO.getInstance(updshop), 200);
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
