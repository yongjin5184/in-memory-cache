package com.mall.commerce.controller.dto;

import com.mall.commerce.entity.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResponse {
    private String productName;
    private BigDecimal productPrice;

    private ProductResponse() {
    }

    public static ProductResponse of(Product product) {
        ProductResponse productResponse = new ProductResponse();

        productResponse.productName = product.getProductName();
        productResponse.productPrice = product.getProductPrice();

        return productResponse;
    }
}
