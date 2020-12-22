package com.mall.commerce.controller.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateProductRequest {
    private String productName;
    private BigDecimal productPrice;
}
