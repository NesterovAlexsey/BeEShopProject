package com.onlineshop.controller;

import com.onlineshop.controller.dto.ShopDTO;
import com.onlineshop.service.ShopService;
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
    public ShopDTO addShop(@RequestBody ShopDTO shopDTO) {
        return shopService.addShop(shopDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ShopDTO> updateShop(@RequestBody ShopDTO shopDTO, @PathVariable Integer id) {
        ShopDTO shopDTO1 = shopService.update(id, shopDTO);
        if (shopDTO1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shopDTO1);
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
