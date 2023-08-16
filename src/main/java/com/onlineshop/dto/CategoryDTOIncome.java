package com.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CategoryDTOIncome {
    private String categoryName;
    private String description;
}
