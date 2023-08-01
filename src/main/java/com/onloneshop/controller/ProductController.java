package com.onloneshop.controller;

import com.onloneshop.controller.dto.ProductDTO;
import com.onloneshop.controller.dto.ProductsDTO;
import com.onloneshop.domain.Product;
import com.onloneshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Product operation:
 * -find all products;
 * -find product by id;
 * -find position by part of name;
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ProductsDTO findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @GetMapping("/category/{categoryId}")
    public ProductsDTO findByCategory(@PathVariable Integer categoryId) {
        return productService.findByCategoryId(categoryId);
    }
}
