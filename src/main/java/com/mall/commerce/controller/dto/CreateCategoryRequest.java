package com.mall.commerce.controller.dto;

import lombok.Getter;

@Getter
public class CreateCategoryRequest {
    private String categoryName;
    private Long parentId;
    private Integer depth;
}
