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
    public ResponseEntity<CountryDTO> addCountry(@RequestBody CountryDTO country) {
        CountryDTO countryDTO = countryService.add(country);
        return ResponseEntity.ok(countryDTO);
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
    public ResponseEntity<CountryDTO> deleteCountry(@PathVariable Integer id) {
        CountryDTO countryDTO = countryService.delete(id);
        if (countryDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(countryDTO);
    }

}
