package com.onloneshop.controller;

import com.onloneshop.controller.dto.CountryDTO;
import com.onloneshop.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/country")
public class AdminCountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/all")
    private List<CountryDTO> findAll(){
        return countryService.findAll();
    }

    @PostMapping("/add")
    public CountryDTO addCountry(@RequestBody CountryDTO country) {
        return countryService.add(country);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO country, @PathVariable Integer id) {
        CountryDTO countryDTO = countryService.update(id, country);
        if (countryDTO != null) {
            return ResponseEntity.ok(countryDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public CountryDTO deleteCountry(@PathVariable Integer id) {
        return  countryService.delete(id);
    }

}
