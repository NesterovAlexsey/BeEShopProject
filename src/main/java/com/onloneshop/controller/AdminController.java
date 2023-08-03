package com.onloneshop.controller;

import com.onloneshop.controller.dto.CountryDTO;
import com.onloneshop.controller.dto.ShopDTO;
import com.onloneshop.service.CountryService;
import com.onloneshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Administration of db records
 * Control product saves
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ShopService shopService;

    @GetMapping("country/all")
    private List<CountryDTO> findAll(){
        return countryService.findAll();
    }

    @PostMapping("/country/add")
    public CountryDTO addCountry(@RequestBody CountryDTO country) {
        return countryService.add(country);
    }

    @PutMapping("/country/update/{id}")
    public CountryDTO updateCountry(@RequestBody CountryDTO country, @PathVariable Integer id) {
        return countryService.update(id, country);
    }

    @DeleteMapping("country/delete/{id}")
    public CountryDTO deleteCountry(@PathVariable Integer id) {
        return  countryService.delete(id);
    }

    @GetMapping("shop/all")
    private List<ShopDTO> findAllShops() {
        return shopService.findAllShops();
    }

    @PostMapping("/shop/add")
    public ShopDTO addShop(@RequestBody ShopDTO shopDTO) {
        return shopService.addShop(shopDTO);
    }

    @PutMapping("/shop/update/{id}")
    public ShopDTO updateShop(@RequestBody ShopDTO shopDTO, @PathVariable Integer id) {
        return shopService.update(id, shopDTO);
    }

    @DeleteMapping("shop/delete/{id}")
    public ShopDTO deleteShop(@PathVariable Integer id) {
        return shopService.deleteShop(id);
    }
}
