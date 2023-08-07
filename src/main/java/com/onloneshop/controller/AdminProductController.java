package com.onloneshop.controller;

import com.onloneshop.controller.dto.ProductDTO;
import com.onloneshop.controller.dto.ProductsDTO;
import com.onloneshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ProductDTO update(@RequestBody ProductDTO productDTO, @PathVariable Integer id) {
        return productService.update(id, productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ProductDTO delete(@PathVariable Integer id) {
        return productService.delete(id);
    }
}
