package com.onloneshop.service;

import com.onloneshop.controller.dto.ProductDTO;
import com.onloneshop.controller.dto.ProductsDTO;
import com.onloneshop.domain.Category;
import com.onloneshop.domain.Product;
import com.onloneshop.domain.Supplier;
import com.onloneshop.repository.CategoryRepository;
import com.onloneshop.repository.ProductRepository;
import com.onloneshop.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

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

    public ProductsDTO findByPartName(String partName) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + partName + '%');
        return ProductsDTO.getInstance(products);
    }

    public ProductDTO add(ProductDTO productDTO) {
        Product product = new Product();
        //find and add category
        Integer categoryId = productDTO.getCategory().getCategoryId();
        Optional<Category> optCategory = categoryRepository.findById(categoryId);
        if (!optCategory.isPresent()) {
            log.error("Not found category categoryId: {}", categoryId);
            return null;
        }
        product.setCategory(optCategory.get());

        //find supplies
        Integer supplierId = productDTO.getSupplier().getSupplierId();
        Optional<Supplier> optSupplier = supplierRepository.findById(supplierId);
        if (!optSupplier.isPresent()) {
            log.error("Not found supplier supplierId: {}", supplierId);
            return null;
        }
        product.setSupplier(optSupplier.get());

        //add other field
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setIsDeleted(false);

        product = productRepository.save(product);
        log.info("Product added successfuly productId: {}", product.getProductId());
        return ProductDTO.getInstance(product);
    }

    public ProductDTO update(Integer id, ProductDTO productDTO) {
        Optional<Product> optProduct = productRepository.findById(id);
        if (!optProduct.isPresent()) {
            return null;
        }
        Product product = optProduct.get();

        //find and add category
        Integer categoryId = productDTO.getCategory().getCategoryId();
        Optional<Category> optCategory = categoryRepository.findById(categoryId);
        if (!optCategory.isPresent()) {
            return null;
        }
        product.setCategory(optCategory.get());

        //find supplies
        Integer supplierId = productDTO.getSupplier().getSupplierId();
        Optional<Supplier> optSupplier = supplierRepository.findById(supplierId);
        if (!optSupplier.isPresent()) {
            return null;
        }
        product.setSupplier(optSupplier.get());

        //add other field
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setIsDeleted(productDTO.getIsDeleted());

        product = productRepository.save(product);
        return ProductDTO.getInstance(product);
    }

    public ProductDTO delete(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product delProduct = product.get();
            productRepository.delete(delProduct);
            return ProductDTO.getInstance(delProduct);
        }
        return null;
    }
}
