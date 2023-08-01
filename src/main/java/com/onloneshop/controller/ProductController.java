package com.onloneshop.controller;

import com.onloneshop.controller.dto.ProductDTO;
import com.onloneshop.controller.dto.ProductsDTO;
import com.onloneshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Open operation for all users
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ProductsDTO findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @GetMapping("/all/category/{categoryId}")
    public ProductsDTO findByCategory(@PathVariable Integer categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    @GetMapping("/byPartName/{partName}")
    public ProductsDTO findByPartName(@PathVariable String partName) {
        return productService.findByPartName(partName);
    }
}
