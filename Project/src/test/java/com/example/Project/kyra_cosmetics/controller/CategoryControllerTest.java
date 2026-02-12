package com.example.Project.kyra_cosmetics.controller;

import com.example.Project.controller.CategoryController;
import com.example.Project.model.Category;
import com.example.Project.service.CategoryService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    MockMvc mvc;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    com.example.Project.controller.CategoryController categoryController;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void create_callsService() throws Exception {
        Category in = Category.builder().name("c").build();
        Category out = Category.builder().id(2L).name("c").build();

        when(categoryService.create(any(Category.class))).thenReturn(out);

        mvc.perform(post("/api/categories").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk()).andExpect(jsonPath(".id").value(2));
    }

    @Test
    void getAll_returnsList() throws Exception {
        when(categoryService.findAll()).thenReturn(List.of(Category.builder().id(3L).name("x").build()));

        mvc.perform(get("/api/categories")).andExpect(status().isOk()).andExpect(jsonPath("[0].id").value(3));
    }
}
