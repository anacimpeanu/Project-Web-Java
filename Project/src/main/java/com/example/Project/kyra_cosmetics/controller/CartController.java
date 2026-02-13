package com.example.Project.kyra_cosmetics.controller;


import com.example.Project.kyra_cosmetics.model.Cart;
import com.example.Project.kyra_cosmetics.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "Shopping Cart", description = "Operations for managing user shopping carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    @Operation(
        summary = "Get user's cart",
        description = "Retrieves the shopping cart for a specific user"
    )
    public Cart getCart(
            @PathVariable 
            @Parameter(description = "User ID", example = "1") 
            Long userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/{userId}/add")
    @Operation(
        summary = "Add product to cart",
        description = "Adds a product with specified quantity to user's cart"
    )
    public Cart addProduct(
            @PathVariable 
            @Parameter(description = "User ID", example = "1") 
            Long userId,
            @RequestParam 
            @Parameter(description = "Product ID to add", example = "1") 
            Long productId,
            @RequestParam 
            @Parameter(description = "Quantity to add", example = "2") 
            int quantity) {
        return cartService.addProduct(userId, productId, quantity);
    }

    @DeleteMapping("/{userId}/remove")
    @Operation(
        summary = "Remove product from cart",
        description = "Removes a product from user's cart"
    )
    public void removeProduct(
            @PathVariable 
            @Parameter(description = "User ID", example = "1") 
            Long userId,
            @RequestParam 
            @Parameter(description = "Product ID to remove", example = "1") 
            Long productId) {
        cartService.removeProduct(userId, productId);
    }
}
