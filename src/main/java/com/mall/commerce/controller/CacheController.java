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
    @GetMapping("/categories/{name}/products")
    public List<Product> findProductsOnCache(@PathVariable(value = "name") String key) {
        return cacheService.findAllProductOnCache(key);
    }

    /**
     * 캐쉬 Refresh
     */
    @GetMapping("/categories/{name}/products/refresh")
    public List<Product> putProductsOnCache(@PathVariable(value = "name") String key) {
        return cacheService.putProductsOnCache(key);
    }
    /**
     * 캐쉬에 해당 상품이 있는지 조회한다.
     */
    @GetMapping("/categories/{name}/products/{productId}")
    public Product findProductsOnCacheByProductId(
            @PathVariable(value = "name") String key, @PathVariable(value = "productId") Long productId) {
        return cacheService.findProductsOnCacheByProductId(key, productId);
    }
}
