package com.example.Project.kyra_cosmetics.controller;

import com.example.Project.controller.ProductController;
import com.example.Project.model.Category;
import com.example.Project.model.Product;
import com.example.Project.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    MockMvc mvc;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void getAll_returnsList() throws Exception {
        Product p = Product.builder().id(1L).name("x").price(1.0).stock(2).build();
        when(productService.findAll()).thenReturn(List.of(p));

        mvc.perform(get("/api/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(1));
    }

    @Test
    void create_callsService() throws Exception {
        Category c = Category.builder().id(2L).name("cat").build();
        Product in = Product.builder().name("n").price(1.0).stock(3).build();
        Product out = Product.builder().id(5L).name("n").price(1.0).stock(3).category(c).build();

        when(productService.create(any(Product.class), eq(2L))).thenReturn(out);

        mvc.perform(post("/api/products").param("categoryId","2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.category.id").value(2));
    }

    @Test
    void delete_callsService() throws Exception {
        doNothing().when(productService).delete(3L);

        mvc.perform(delete("/api/products/3")).andExpect(status().isOk());

        verify(productService).delete(3L);
    }
}
