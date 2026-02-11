package com.example.Project.controller;


import com.example.Project.model.Product;
import com.example.Project.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product create(@Valid @RequestBody Product product,
                          @RequestParam Long categoryId) {
        return productService.create(product, categoryId);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable Long categoryId) {
        return productService.findByCategory(categoryId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
