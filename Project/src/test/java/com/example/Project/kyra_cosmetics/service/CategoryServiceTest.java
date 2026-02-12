package com.example.Project.kyra_cosmetics.service;

import com.example.Project.exception.BadRequestException;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.model.Category;
import com.example.Project.repository.CategoryRepository;
import com.example.Project.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    void create_whenNameExists_throws() {
        Category c = Category.builder().name("c").build();
        when(categoryRepository.existsByName("c")).thenReturn(true);

        assertThrows(BadRequestException.class, () -> categoryService.create(c));
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void create_savesCategory() {
        Category c = Category.builder().name("new").build();
        when(categoryRepository.existsByName("new")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenAnswer(inv -> {
            Category cat = inv.getArgument(0);
            cat.setId(2L);
            return cat;
        });

        Category saved = categoryService.create(c);

        assertNotNull(saved.getId());
        assertEquals("new", saved.getName());
    }
}
