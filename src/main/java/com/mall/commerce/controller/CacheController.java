package com.mall.commerce.controller;

import com.mall.commerce.entity.Product;
import com.mall.commerce.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class CacheController {

    private final CacheService cacheService;

    /**
     * 캐쉬에서 상품을 조회하여 반환한다.
     * 캐쉬 : KEY- 카테고리이름, VALUE- 상품리스트
     */
    @GetMapping("/categories/{name}/product")
    public List<Product> findProductsOnCache(@PathVariable(value = "name") String key) {
        List<Product> products  = cacheService.findAllProductOnCache(key);

        return products;
    }

    @GetMapping("/categories/{name}/product/{productId}")
    public Product findProductsOnCacheByProductId(
            @PathVariable(value = "name") String key, @PathVariable(value = "productId") Long productId) {
        Product product  = cacheService.findProductsOnCacheByProductId(key, productId);

        return product;
    }
}
