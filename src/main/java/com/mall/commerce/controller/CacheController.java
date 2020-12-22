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

    @GetMapping("/category/{name}/product")
    public List<Product> getProductsOnCache(@PathVariable(value = "name") String key) {
        List<Product> products  = cacheService.findAllProductOnCache(key);

        return products;
    }
}
