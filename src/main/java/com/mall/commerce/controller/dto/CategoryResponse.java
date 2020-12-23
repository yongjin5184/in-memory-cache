package com.mall.commerce.controller.dto;

import com.mall.commerce.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponse {

    private Long id;
    private String categoryName;
    private Long parentId;
    private String depth;

    private CategoryResponse() {
    }

    public static CategoryResponse of(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.id = category.getId();
        categoryResponse.categoryName = category.getCategoryName();
        categoryResponse.parentId = category.getParentId();
        categoryResponse.depth = category.getDepth();

        return categoryResponse;
    }
}
