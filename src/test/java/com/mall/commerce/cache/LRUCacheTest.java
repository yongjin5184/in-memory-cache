package com.mall.commerce.cache;

import com.mall.commerce.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LRUCacheTest {

    private LRUCache lruCache;

    @BeforeEach
    public void setUp() {
        lruCache = new LRUCache(10000);
    }

    @Test
    @DisplayName("Cache Miss 인 경우, 스킨케어-남성 카테고리의 데이터를 원본 DB 에서 로딩하여, Cache 의 사이즈를 확인한다.")
    void reloadingProductWhenCacheMiss (){
        //given
        List<Product> productsWhenCacheMiss = lruCache.doGet("스킨케어-남성");

        //when
        List<Product> productsWhenCacheHit = lruCache.doGet("스킨케어-남성");

        //then
        assertNull(productsWhenCacheMiss);
        assertEquals(100, productsWhenCacheHit.size());
    }
}