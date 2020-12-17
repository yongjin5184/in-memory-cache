package com.mall.commerce.cache;

import com.mall.commerce.entity.Product;

import java.util.*;

public class LRUCache extends LinkedHashMap<String, List<Product>> implements Cache {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, List<Product>> eldest) {
        return size() > capacity;
    }

    @Override
    public List<Product> doGet(String key) {
        return get(key);
    }

    @Override
    public void doPut(String key, List value) {
        put(key, value);
    }
}
