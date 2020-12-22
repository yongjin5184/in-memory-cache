package com.mall.commerce.service;

import com.mall.commerce.controller.dto.UpdateProductRequest;
import com.mall.commerce.entity.Product;
import com.mall.commerce.exception.NotExistProductException;
import com.mall.commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Long categoryId) {
        return productRepository.findById(categoryId).orElseThrow(NotExistProductException::new);
    }

    @Transactional
    public Product update(Long productId, UpdateProductRequest updateProductRequest) {
        Product findProduct = findById(productId);

        findProduct.update(updateProductRequest.getProductName(), updateProductRequest.getProductPrice());

        return findProduct;
    }
}
