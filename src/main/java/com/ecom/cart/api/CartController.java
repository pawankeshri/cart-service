package com.ecom.cart.api;

import com.ecom.cart.api.dto.CartItemRequest;
import com.ecom.cart.api.dto.CartItemResponse;
import com.ecom.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts/{cartId}/items")
@Tag(name = "Cart API", description = "Operations for managing cart items")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "Add product to cart")
    @PostMapping
    public ResponseEntity<CartItemResponse> addItem(
            @PathVariable String cartId,
            @Valid @RequestBody CartItemRequest request) {

        CartItemResponse response = cartService.addItem(cartId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update quantity of an existing product")
    @PutMapping("/{productId}")
    public ResponseEntity<CartItemResponse> updateItemQuantity(
            @PathVariable String cartId,
            @PathVariable String productId,
            @RequestParam("quantity") int quantity) {

        CartItemResponse response = cartService.updateItemQuantity(cartId, productId, quantity);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remove product from cart")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeItem(
            @PathVariable String cartId,
            @PathVariable String productId) {

        cartService.removeItem(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all items in cart")
    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getItems(@PathVariable String cartId) {
        return ResponseEntity.ok(cartService.getItems(cartId));
    }
}
