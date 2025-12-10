package com.ecom.cart.service.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String cartId, String productId) {
        super("Cart item not found for cartId=" + cartId + ", productId=" + productId);
    }
}
