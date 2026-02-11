package com.example.Project.service;


import com.example.Project.exception.BadRequestException;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.model.Category;
import com.example.Project.repository.CategoryRepository;
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
