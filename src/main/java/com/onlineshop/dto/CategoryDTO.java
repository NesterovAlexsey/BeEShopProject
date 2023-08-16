package com.onlineshop.dto;

import com.onlineshop.domain.Category;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class CategoryDTO {
    private final Integer categoryId;
    private final String categoryName;
    private final String description;

    public static CategoryDTO getInstance(Category category) {
        return new CategoryDTO(category.getCategoryId(), category.getCategoryName(), category.getDescription());
    }
}
