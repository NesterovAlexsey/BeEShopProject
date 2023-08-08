package com.onlineshop.controller;

import com.onlineshop.controller.dto.SupplierDTO;
import com.onlineshop.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SupplierDTO> update(@RequestBody SupplierDTO supplierDTO, @PathVariable Integer id) {
        SupplierDTO supplierDTO1 = supplierService.update(supplierDTO, id);
        if (supplierDTO1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplierDTO1);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<SupplierDTO> delete(@PathVariable Integer id) {
        SupplierDTO supplierDTO1 = supplierService.delete(id);
        if (supplierDTO1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplierDTO1);
    }
}
