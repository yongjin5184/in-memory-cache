package com.mall.commerce.utils;

public final class CacheUtils {

    public static String split(String key) {
        String[] arr = key.split("-");

        return arr.length == 1 ? arr[0] : arr[1];
    }
}
