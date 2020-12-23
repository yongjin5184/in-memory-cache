package com.mall.commerce.exception;

public class NotExistProductException extends RuntimeException {
    public NotExistProductException() {
        super("존재하지 않는 상품입니다.");
    }
}
