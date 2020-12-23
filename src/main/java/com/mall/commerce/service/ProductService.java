package com.mall.commerce.service;

import com.mall.commerce.controller.dto.UpdateProductRequest;
import com.mall.commerce.entity.Category;
import com.mall.commerce.entity.Product;
import com.mall.commerce.exception.NotExistProductException;
import com.mall.commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    /**
     *
     * @param productId
     * @return 상품 ID 에 해당하는 상품
     */
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElseThrow(NotExistProductException::new);
    }


    /**
     *
     * @param productId
     * @param updateProductRequest
     * @return 변경된 상품
     */
    @Transactional
    public Product update(Long productId, UpdateProductRequest updateProductRequest) {
        Product findProduct = findById(productId);

        findProduct.update(updateProductRequest.getProductName(), updateProductRequest.getProductPrice());

        return findProduct;
    }

    /**
     *
     * @param productId
     * @return 단일 상품
     */
    public Product findByProductId(Long productId) {
        return productRepository.findById(productId).orElseThrow(NotExistProductException::new);
    }

    /**
     *
     * @return 전체 상품 리스트
     */
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     *
     * @param category
     * @return 특정 카테고리에 속한 상품 리스트
     */
    public List<Product> findAllProductsByCategory(Category category) {
        return productRepository.findAllProductsByCategory(category);
    }
}
