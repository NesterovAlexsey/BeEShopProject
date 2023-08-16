package com.onlineshop.controller;

import com.onlineshop.dto.ShopDTO;
import com.onlineshop.service.ShopService;
import com.onlineshop.service.ShopServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Administration of db records
 * Control product saves
 */
@RestController
@RequestMapping("/admin/shop")
public class AdminShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/all")
    private List<ShopDTO> findAllShops() {
        return shopService.findAllShops();
    }

    @PostMapping("/add")
    public ResponseEntity<ShopDTO> addShop(@RequestBody ShopDTO shopDTO) {
        ShopDTO shopDTO1 = shopService.addShop(shopDTO);
        if (shopDTO1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shopDTO1);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ShopDTO> updateShop(@RequestBody ShopDTO shopDTO, @PathVariable Integer id) {
        ShopServiceResponse shopServiceResponse = shopService.update(id, shopDTO);
        ResponseEntity<ShopDTO> result = null;
        switch (shopServiceResponse.getResponseResult()) {
            case 200:
                result = ResponseEntity.ok(shopServiceResponse.getShopDTO());
                break;
            case 404:
                result = ResponseEntity.notFound().build();
                break;
            case 400:
                result = ResponseEntity.badRequest().build();
                break;
        }
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ShopDTO> deleteShop(@PathVariable Integer id) {
        ShopDTO shopDTO1 = shopService.deleteShop(id);
        if (shopDTO1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shopDTO1);
    }
}
