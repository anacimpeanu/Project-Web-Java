package com.example.Project.kyra_cosmetics.service;

import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.model.Category;
import com.example.Project.model.Product;
import com.example.Project.repository.CategoryRepository;
import com.example.Project.repository.ProductRepository;
import com.example.Project.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void create_whenCategoryMissing_throws() {
        Product p = Product.builder().name("prod").price(1.0).stock(10).build();
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.create(p, 1L));
    }

    @Test
    void create_setsCategoryAndSaves() {
        Category cat = Category.builder().id(2L).name("c").build();
        Product p = Product.builder().name("prod").price(1.0).stock(10).build();

        when(categoryRepository.findById(2L)).thenReturn(Optional.of(cat));
        when(productRepository.save(any(Product.class))).thenAnswer(inv -> {
            Product prod = inv.getArgument(0);
            prod.setId(5L);
            return prod;
        });

        Product saved = productService.create(p, 2L);

        assertEquals(cat, saved.getCategory());
        assertNotNull(saved.getId());
        verify(productRepository).save(saved);
    }
}
