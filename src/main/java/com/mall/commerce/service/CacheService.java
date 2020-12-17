package com.mall.commerce.service;

import com.mall.commerce.entity.Category;
import com.mall.commerce.entity.Product;
import com.mall.commerce.exception.NotExistProductException;
import com.mall.commerce.repository.CategoryRepository;
import com.mall.commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CacheService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     *
     * @return 모든 상품 리스트
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

    /**
     *
     * @param productNo
     * @return 단일 상품
     */
    public Product findByProductNo(Long productNo) {
        return productRepository.findById(productNo).orElseThrow(NotExistProductException::new);
    }

    /**
     *
     * @return 모든 카테고리 리스트
     */
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     *
     * @param parentNo
     * @return 부모 카테고리에 속하는 카테고리 리스트
     */
    public List<Category> findAllCategoriesByParentNo(int parentNo) {
        return categoryRepository.findAllByParentNo(parentNo);
    }


}
