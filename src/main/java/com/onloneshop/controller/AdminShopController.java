package com.onloneshop.controller;

import com.onloneshop.controller.dto.ShopDTO;
import com.onloneshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ShopDTO updateShop(@RequestBody ShopDTO shopDTO, @PathVariable Integer id) {
        return shopService.update(id, shopDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ShopDTO deleteShop(@PathVariable Integer id) {
        return shopService.deleteShop(id);
    }
}
