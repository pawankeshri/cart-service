package com.ecom.cart.api.mapper;

import com.ecom.cart.api.dto.CartItemRequest;
import com.ecom.cart.api.dto.CartItemResponse;
import com.ecom.cart.domain.CartItem;

public final class CartItemMapper {

    private CartItemMapper() {
    }

    public static CartItem toEntity(String cartId, CartItemRequest request) {
        return new CartItem(
                cartId,
                request.getProductId(),
                request.getProductName(),
                request.getUnitPrice(),
                request.getQuantity()
        );
    }

    public static CartItemResponse toResponse(CartItem entity) {
        return new CartItemResponse(
                entity.getCartId(),
                entity.getProductId(),
                entity.getProductName(),
                entity.getUnitPrice(),
                entity.getQuantity()
        );
    }
}
