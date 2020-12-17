package com.mall.commerce.cache;

import com.mall.commerce.config.ApplicationContextProvider;
import com.mall.commerce.entity.Category;
import com.mall.commerce.entity.Product;
import com.mall.commerce.exception.NotExistCategoryProductException;
import com.mall.commerce.repository.CategoryRepository;
import com.mall.commerce.repository.ProductRepository;
import com.mall.commerce.utils.CacheUtils;
import org.springframework.util.CollectionUtils;


import java.util.*;

public class LRUCache extends LinkedHashMap<String, List<Product>> implements Cache {
    private int capacity;

    private ProductRepository getProductRepository() {
        return ApplicationContextProvider.getBean(ProductRepository.class);
    }

    private CategoryRepository getCategoryRepository() {
        return ApplicationContextProvider.getBean(CategoryRepository.class);
    }

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, List<Product>> eldest) {
        return size() > capacity;
    }

    @Override
    public List<Product> doGet(String key) throws NotExistCategoryProductException {
        List<Product> products = get(key);

        /**
         * Cache Miss
         */
        if (CollectionUtils.isEmpty(products)) {
            List<Product> refreshProducts = getRefreshProducts(key);
            if (CollectionUtils.isEmpty(refreshProducts)) {
                throw new NotExistCategoryProductException();
            }
            put(key, refreshProducts);
        }

        return products;
    }

    /**
     *
     * @param key
     * @return 카테고리에 해당하는 상품 리스트
     */
    public List<Product> getRefreshProducts(String key) {
        String CategoryName = CacheUtils.split(key);
        Category category = getCategoryRepository().findByCategoryName(CategoryName);

        return getProductRepository().findAllProductsByCategory(category);
    }

    @Override
    public void doPut(String key, List value) {
        put(key, value);
    }
}
