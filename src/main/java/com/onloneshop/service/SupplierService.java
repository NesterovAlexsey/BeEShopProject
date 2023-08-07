package com.onloneshop.service;

import com.onloneshop.controller.dto.SupplierDTO;
import com.onloneshop.domain.Country;
import com.onloneshop.domain.Supplier;
import com.onloneshop.repository.CountryRepository;
import com.onloneshop.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CountryRepository countryRepository;

    public List<SupplierDTO> findAll() {
        List<SupplierDTO> suppliersDTO = new ArrayList<>();
        List<Supplier> suppliers = supplierRepository.findAll();
        suppliers.forEach(supplier -> suppliersDTO.add(SupplierDTO.getInstance(supplier)));
        return suppliersDTO;
    }

    public SupplierDTO add(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();

        Integer countryId = supplierDTO.getCountry().getCountryId();
        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        if (optionalCountry.isEmpty()) {
            return null;
        }

        supplier.setSupplierName(supplierDTO.getSupplierName());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setCountry(optionalCountry.get());

        supplier = supplierRepository.save(supplier);

        return SupplierDTO.getInstance(supplier);
    }

    public SupplierDTO update(SupplierDTO supplierDTO, Integer id) {
        Optional<Supplier> optSupplier = supplierRepository.findById(id);
        if (optSupplier.isEmpty()) {
            return null;
        }
        Supplier supplier = optSupplier.get();

        Integer countryId = supplierDTO.getCountry().getCountryId();
        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        if (optionalCountry.isEmpty()) {
            return null;
        }

        supplier.setSupplierName(supplierDTO.getSupplierName());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setCountry(optionalCountry.get());

        supplier = supplierRepository.save(supplier);

        return SupplierDTO.getInstance(supplier);
    }

    public SupplierDTO delete(Integer id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isEmpty()) {
            return null;
        }

        supplierRepository.delete(supplier.get());
        return SupplierDTO.getInstance(supplier.get());
    }
}
