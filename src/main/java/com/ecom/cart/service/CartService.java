package com.ecom.cart.service;

import com.ecom.cart.api.dto.CartItemRequest;
import com.ecom.cart.api.dto.CartItemResponse;

import java.util.List;

public interface CartService {

    CartItemResponse addItem(String cartId, CartItemRequest request);

    CartItemResponse updateItemQuantity(String cartId, String productId, int quantity);

    void removeItem(String cartId, String productId);

    List<CartItemResponse> getItems(String cartId);
}
