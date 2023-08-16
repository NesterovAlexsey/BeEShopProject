package com.onlineshop.controller;

import com.onlineshop.controller.api.AdminCategoryControllerAPI;
import com.onlineshop.dto.CategoryDTO;
import com.onlineshop.service.impl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class AdminCategoryController implements AdminCategoryControllerAPI {

    private final CategoryService categoryService;

    @Override
    public List<CategoryDTO> findAllCategory() { //видео марселя
        return categoryService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.add(categoryDTO);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Integer id) {
        CategoryDTO category = categoryService.update(id, categoryDTO);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Integer id) {
        CategoryDTO category = categoryService.delete(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }
}
