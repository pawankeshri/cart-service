package com.ecom.cart.api.dto;

import java.math.BigDecimal;

public class CartItemResponse {

    private String cartId;
    private String productId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;

    public CartItemResponse(String cartId, String productId, String productName,
                            BigDecimal unitPrice, Integer quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getCartId() {
        return cartId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
