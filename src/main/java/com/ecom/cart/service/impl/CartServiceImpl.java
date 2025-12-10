package com.ecom.cart.service.impl;

import com.ecom.cart.api.dto.CartItemRequest;
import com.ecom.cart.api.dto.CartItemResponse;
import com.ecom.cart.api.mapper.CartItemMapper;
import com.ecom.cart.domain.CartItem;
import com.ecom.cart.repository.CartItemRepository;
import com.ecom.cart.service.CartService;
import com.ecom.cart.service.exception.CartItemNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItemResponse addItem(String cartId, CartItemRequest request) {
        CartItem cartItem = cartItemRepository
                .findByCartIdAndProductId(cartId, request.getProductId())
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + request.getQuantity());
                    return existing;
                })
                .orElseGet(() -> CartItemMapper.toEntity(cartId, request));

        CartItem saved = cartItemRepository.save(cartItem);
        return CartItemMapper.toResponse(saved);
    }

    @Override
    public CartItemResponse updateItemQuantity(String cartId, String productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new CartItemNotFoundException(cartId, productId));

        cartItem.setQuantity(quantity);
        CartItem saved = cartItemRepository.save(cartItem);

        return CartItemMapper.toResponse(saved);
    }

    @Override
    public void removeItem(String cartId, String productId) {
        boolean exists = cartItemRepository.existsByCartIdAndProductId(cartId, productId);
        if (!exists) {
            throw new CartItemNotFoundException(cartId, productId);
        }
        cartItemRepository.deleteByCartIdAndProductId(cartId, productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemResponse> getItems(String cartId) {
        return cartItemRepository.findByCartId(cartId)
                .stream()
                .map(CartItemMapper::toResponse)
                .toList();
    }
}
