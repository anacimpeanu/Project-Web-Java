package com.kyra.cosmetics.service;

import com.kyra.cosmetics.exception.BadRequestException;
import com.kyra.cosmetics.exception.ResourceNotFoundException;
import com.kyra.cosmetics.model.Category;
import com.kyra.cosmetics.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(Category category) {

        if (categoryRepository.existsByName(category.getName())) {
            throw new BadRequestException("Category already exists");
        }

        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
}
