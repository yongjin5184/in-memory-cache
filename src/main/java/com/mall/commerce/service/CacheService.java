package com.mall.commerce.service;

import com.mall.commerce.cache.ConcurrentLRUCache;
import com.mall.commerce.entity.Category;
import com.mall.commerce.entity.Product;
import com.mall.commerce.exception.NotExistCategoryProductException;
import com.mall.commerce.utils.CacheUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CacheService {

    private final ProductService productService;
    private final CategoryService categoryService;
    private ConcurrentLRUCache lruCache = new ConcurrentLRUCache(1000);

    @PostConstruct
    private void init() {
        List<Category> allCategories = categoryService.findAllCategories();
        for (Category parentCategory : allCategories) {
            List<Product> productsByCategory = productService.findAllProductsByCategory(parentCategory);
            if(parentCategory.getParentId() == null) {
                lruCache.put(parentCategory.getCategoryName(), productsByCategory);
            }

            List<Category> childCategories = categoryService.findAllCategoriesByParentId(parentCategory.getId());
            for (Category childCategory : childCategories) {
                lruCache.put(parentCategory.getCategoryName() + "-" + childCategory.getCategoryName(), productsByCategory);
            }
        }
    }

    /**
     * Cache Eviction 조건
     * Cache Miss 가 발생하였을 때, Refresh 될 새로운 상품 데이터가 있다고 하면, 가장 오랫동안 사용하지 않은 데이터를 삭제한다.
     * <p>
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

            if (lruCache.isFullCacheSize()) {
                lruCache.evict();
            }

            lruCache.put(key, refreshProducts);
        }

        return (List<Product>) lruCache.get(key);
    }

    public Product findProductsOnCacheByProductId(String key, Long productId) {
        List<Product> products = (List<Product>) lruCache.get(key);
        if (CollectionUtils.isEmpty(products)) {
            return null;
        }

        Product findProduct = null;
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                findProduct = product;
            }
        }

        return findProduct;
    }

    /**
     * @param key
     * @return 카테고리에 해당하는 상품 리스트
     */
    public List<Product> getRefreshProducts(String key) {
        String CategoryName = CacheUtils.split(key);
        Category category = categoryService.findByCategoryName(CategoryName);

        return productService.findAllProductsByCategory(category);
    }
}
