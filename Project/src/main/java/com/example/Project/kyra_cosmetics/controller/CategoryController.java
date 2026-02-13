package com.example.Project.kyra_cosmetics.controller;


import com.example.Project.kyra_cosmetics.model.Category;
import com.example.Project.kyra_cosmetics.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Category Management", description = "Operations for managing product categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(
        summary = "Create a new category",
        description = "Adds a new product category for organizing cosmetic products"
    )
    public Category create(
            @Valid @RequestBody 
            @Parameter(description = "Category details") 
            Category category) {
        return categoryService.create(category);
    }

    @GetMapping
    @Operation(
        summary = "Get all categories",
        description = "Retrieves all available product categories"
    )
    public List<Category> getAll() {
        return categoryService.findAll();
    }
}
