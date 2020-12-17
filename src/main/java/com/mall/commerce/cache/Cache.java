package com.mall.commerce.cache;

import java.util.List;

public interface Cache<T> {
    List<T> doGet(String key);

    void doPut(String key, List<T> value);
}
