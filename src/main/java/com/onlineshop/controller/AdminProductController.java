package com.onlineshop.controller;

import com.onlineshop.controller.dto.ProductDTO;
import com.onlineshop.controller.dto.ProductsDTO;
import com.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/product")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ProductsDTO findAll() {
        return productService.findAll();
    }

    @PostMapping("/add")
    public ProductDTO add(@RequestBody ProductDTO productDTO) {
        return productService.add(productDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO, @PathVariable Integer id) {
        ProductDTO productResult = productService.update(id, productDTO);
        if (productResult == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productResult);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Integer id) {
        ProductDTO productDTO = productService.delete(id);
        if (productDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTO);
    }
}
