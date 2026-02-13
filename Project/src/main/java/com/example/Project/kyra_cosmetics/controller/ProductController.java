package com.example.Project.kyra_cosmetics.controller;


import com.example.Project.kyra_cosmetics.model.Product;
import com.example.Project.kyra_cosmetics.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "Operations for managing cosmetic products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(
        summary = "Create a new product",
        description = "Adds a new cosmetic product to the specified category"
    )
    public Product create(
            @Valid @RequestBody 
            @Parameter(description = "Product details") 
            Product product,
            @RequestParam 
            @Parameter(description = "Category ID to assign the product to", example = "1") 
            Long categoryId) {
        return productService.create(product, categoryId);
    }

    @GetMapping
    @Operation(
        summary = "Get all products",
        description = "Retrieves all available cosmetic products"
    )
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/category/{categoryId}")
    @Operation(
        summary = "Get products by category",
        description = "Retrieves all products from a specific category"
    )
    public List<Product> getByCategory(
            @PathVariable 
            @Parameter(description = "Category ID", example = "1") 
            Long categoryId) {
        return productService.findByCategory(categoryId);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a product",
        description = "Removes a product from the catalog"
    )
    public void delete(
            @PathVariable 
            @Parameter(description = "Product ID", example = "1") 
            Long id) {
        productService.delete(id);
    }
}
