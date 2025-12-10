package com.ecom.cart.api;

import com.ecom.cart.api.dto.CartItemRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addItem_and_getItems_flow() throws Exception {
        CartItemRequest request = new CartItemRequest();
        request.setProductId("P1");
        request.setProductName("Product1");
        request.setUnitPrice(BigDecimal.valueOf(10.5));
        request.setQuantity(3);

        mockMvc.perform(post("/api/v1/carts/CART123/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cartId").value("CART123"))
                .andExpect(jsonPath("$.productId").value("P1"))
                .andExpect(jsonPath("$.quantity").value(3));

        mockMvc.perform(get("/api/v1/carts/CART123/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value("P1"));
    }
}
