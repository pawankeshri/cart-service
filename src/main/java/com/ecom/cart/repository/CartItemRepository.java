package com.ecom.cart.repository;

import com.ecom.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(String cartId);

    Optional<CartItem> findByCartIdAndProductId(String cartId, String productId);

    void deleteByCartIdAndProductId(String cartId, String productId);

    boolean existsByCartIdAndProductId(String cartId, String productId);
}
