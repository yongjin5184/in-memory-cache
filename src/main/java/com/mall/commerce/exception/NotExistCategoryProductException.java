package com.mall.commerce.exception;

public class NotExistCategoryProductException extends RuntimeException {
    public NotExistCategoryProductException() {
        super("존재하지 않는 카테고리의 상품입니다.");
    }
}
