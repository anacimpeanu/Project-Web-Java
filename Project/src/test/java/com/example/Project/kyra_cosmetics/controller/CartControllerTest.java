package com.example.Project.kyra_cosmetics.controller;

import com.example.Project.kyra_cosmetics.controller.CartController;
import com.example.Project.kyra_cosmetics.model.Cart;
import com.example.Project.kyra_cosmetics.model.CartItem;
import com.example.Project.kyra_cosmetics.model.Product;
import com.example.Project.kyra_cosmetics.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    MockMvc mvc;

    @Mock
    CartService cartService;

    @InjectMocks
    com.example.Project.kyra_cosmetics.controller.CartController cartController;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    void getCart_returnsCart() throws Exception {
        Cart c = Cart.builder().id(2L).cartItems(List.of(CartItem.builder().id(5L).product(Product.builder().id(7L).build()).build())).build();
        when(cartService.getCartByUser(9L)).thenReturn(c);

        mvc.perform(get("/api/cart/9")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void addProduct_callsService() throws Exception {
        Cart c = Cart.builder().id(2L).build();
        when(cartService.addProduct(4L,5L,3)).thenReturn(c);

        mvc.perform(post("/api/cart/4/add").param("productId","5").param("quantity","3"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void removeProduct_callsService() throws Exception {
        doNothing().when(cartService).removeProduct(4L,6L);

        mvc.perform(delete("/api/cart/4/remove").param("productId","6")).andExpect(status().isOk());

        verify(cartService).removeProduct(4L,6L);
    }
}
