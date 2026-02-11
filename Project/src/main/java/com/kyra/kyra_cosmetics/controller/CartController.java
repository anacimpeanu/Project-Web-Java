package com.kyra.cosmetics.controller;

import com.kyra.cosmetics.model.Cart;
import com.kyra.cosmetics.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/{userId}/add")
    public Cart addProduct(@PathVariable Long userId,
                           @RequestParam Long productId,
                           @RequestParam int quantity) {
        return cartService.addProduct(userId, productId, quantity);
    }

    @DeleteMapping("/{userId}/remove")
    public void removeProduct(@PathVariable Long userId,
                              @RequestParam Long productId) {
        cartService.removeProduct(userId, productId);
    }
}
