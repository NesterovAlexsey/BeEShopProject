package com.onloneshop.service;

import com.onloneshop.controller.dto.CountryDTO;
import com.onloneshop.domain.Country;
import com.onloneshop.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;
    public CountryDTO add(CountryDTO countryDTO) {
        Country newCountry = new Country();
        newCountry.setCountryName(countryDTO.getCountryName());
        newCountry = countryRepository.save(newCountry);
        return CountryDTO.getInstance(newCountry);
    }

    public List<CountryDTO> findAll() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDTO> result = new ArrayList<>();
        countries.forEach(country -> result.add(CountryDTO.getInstance(country)));
        return result;
    }
}
