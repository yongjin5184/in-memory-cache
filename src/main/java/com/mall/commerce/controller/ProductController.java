package com.mall.commerce.controller;

import com.mall.commerce.controller.dto.ProductResponse;
import com.mall.commerce.controller.dto.UpdateProductRequest;
import com.mall.commerce.entity.Product;
import com.mall.commerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class ProductController {
    private final ProductService productService;

    /**
     *
     * @param productId
     * @return 특정 상품을 변경한다.
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable("id") Long productId, @RequestBody UpdateProductRequest updateProductRequest) {
        Product product = productService.update(productId, updateProductRequest);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ProductResponse.of(product));
    }
}
