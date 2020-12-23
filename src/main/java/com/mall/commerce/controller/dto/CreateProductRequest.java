package com.mall.commerce.controller.dto;

import com.mall.commerce.entity.Category;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateProductRequest {

    private String brandName;
    private String productName;
    private BigDecimal productPrice;
    private Category category;

}
