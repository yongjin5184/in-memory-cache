package com.mall.commerce.service;

import com.mall.commerce.cache.ConcurrentLRUCache;
import com.mall.commerce.entity.Category;
import com.mall.commerce.entity.Product;
import com.mall.commerce.exception.NotExistCategoryProductException;
import com.mall.commerce.exception.NotExistProductException;
import com.mall.commerce.repository.CategoryRepository;
import com.mall.commerce.repository.ProductRepository;
import com.mall.commerce.utils.CacheUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CacheService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private ConcurrentLRUCache lruCache = new ConcurrentLRUCache(1000);

    @PostConstruct
    private void init () {
        List<Category> allCategories = findAllCategories();
        for (Category parentCategory : allCategories) {
            List<Category> childCategories = findAllCategoriesByParentNo(parentCategory.getId());
            List<Product> productsByCategory = findAllProductsByCategory(parentCategory);
            for (Category childCategory : childCategories) {
                lruCache.put(parentCategory.getCategoryName() + "-" + childCategory.getCategoryName(), productsByCategory);
            }
        }
    }

    /**
     *
     * Cache Eviction 조건
     * Cache Miss 가 발생하였을 때, Refresh 될 새로운 상품 데이터가 있다고 하면, 가장 오랫동안 사용하지 않은 데이터를 삭제한다.
     *
     * Cache Eviction 조건에 대한 이유
     * Cache Memory 가 한정적이라고 한다면, 가장 오래 사용하지 않은 데이터를 지우고, 새로운 데이터를 가져와 넣어주는 것이 합리적이라고 생각
     */
    public List<Product> findAllProductOnCache(String key) {
        List<Product> products = (List<Product>) lruCache.get(key);
        if (CollectionUtils.isEmpty(products)) {
            List<Product> refreshProducts = getRefreshProducts(key);
            if (CollectionUtils.isEmpty(refreshProducts)) {
                throw new NotExistCategoryProductException();
            }

            if(lruCache.isFullCacheSize()) {
                lruCache.evict();
            }

            lruCache.put(key, refreshProducts);
        }

        return (List<Product>) lruCache.get(key);
    }

    /**
     *
     * @param key
     * @return 카테고리에 해당하는 상품 리스트
     */
    public List<Product> getRefreshProducts(String key) {
        String CategoryName = CacheUtils.split(key);
        Category category = categoryRepository.findByCategoryName(CategoryName);

        return productRepository.findAllProductsByCategory(category);
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
    public List<Category> findAllCategoriesByParentNo(Long parentNo) {
        return categoryRepository.findAllByParentNo(parentNo);
    }
}
