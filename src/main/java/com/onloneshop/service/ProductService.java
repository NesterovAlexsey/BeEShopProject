package com.onloneshop.service;

import com.onloneshop.controller.dto.ProductDTO;
import com.onloneshop.controller.dto.ProductsDTO;
import com.onloneshop.domain.Product;
import com.onloneshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductsDTO findAll() {
        return ProductsDTO.getInstance(productRepository.findAll());
    }

    public ProductDTO findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ProductDTO.getInstance(product.get());
        }
        return null;
    }

    public ProductsDTO findByCategoryId(Integer categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return ProductsDTO.getInstance(products);
    }
}
