package com.ecom.cart.service;

import com.ecom.cart.api.dto.CartItemRequest;
import com.ecom.cart.domain.CartItem;
import com.ecom.cart.repository.CartItemRepository;
import com.ecom.cart.service.exception.CartItemNotFoundException;
import com.ecom.cart.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    private CartItemRepository repository;
    private CartService cartService;

    @BeforeEach
    void setUp() {
        repository = mock(CartItemRepository.class);
        cartService = new CartServiceImpl(repository);
    }

    @Test
    void addItem_shouldCreateNewItem_whenNotExists() {
        CartItemRequest request = new CartItemRequest();
        request.setProductId("P1");
        request.setProductName("Mock Product");
        request.setUnitPrice(BigDecimal.valueOf(10.0));
        request.setQuantity(2);

        when(repository.findByCartIdAndProductId("C1", "P1")).thenReturn(Optional.empty());
        when(repository.save(any(CartItem.class))).thenAnswer(inv -> inv.getArgument(0));

        var response = cartService.addItem("C1", request);

        assertThat(response.getCartId()).isEqualTo("C1");
        assertThat(response.getProductId()).isEqualTo("P1");
        assertThat(response.getQuantity()).isEqualTo(2);
    }

    @Test
    void updateItemQuantity_shouldThrow_whenItemNotFound() {
        when(repository.findByCartIdAndProductId("C1", "P1")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> cartService.updateItemQuantity("C1", "P1", 5))
                .isInstanceOf(CartItemNotFoundException.class);
    }
}
