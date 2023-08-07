package com.onloneshop.controller;

import com.onloneshop.controller.dto.SupplierDTO;
import com.onloneshop.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/supplier")
public class AdminSupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/all")
    public List<SupplierDTO> findAll() {
        return supplierService.findAll();
    }

    @PostMapping("/add")
    public SupplierDTO add(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.add(supplierDTO);
    }

    @PutMapping("/update/{id}")
    public SupplierDTO update(@RequestBody SupplierDTO supplierDTO, @PathVariable Integer id) {
        return supplierService.update(supplierDTO, id);
    }

    @DeleteMapping("delete/{id}")
    public SupplierDTO delete(@PathVariable Integer id) {
        return supplierService.delete(id);
    }
}
