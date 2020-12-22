package com.mall.commerce.controller.dto;

import com.mall.commerce.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponse {

    private String categoryName;

    private CategoryResponse() {
    }

    public static CategoryResponse of(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.categoryName = category.getCategoryName();

        return categoryResponse;
    }
}
