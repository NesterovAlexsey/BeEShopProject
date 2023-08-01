package com.onloneshop.controller.dto;

import com.onloneshop.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryDTO {
    private final Integer categoryId;
    private final String categoryName;
    private final String description;

    public static CategoryDTO getInstance(Category category) {
        return new CategoryDTO(category.getCategoryId(), category.getCategoryName(), category.getDescription());
    }
}
