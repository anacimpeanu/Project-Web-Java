package com.example.Project.controller;


import com.example.Project.model.Category;
import com.example.Project.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Category create(@Valid @RequestBody Category category) {
        return categoryService.create(category);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.findAll();
    }
}
