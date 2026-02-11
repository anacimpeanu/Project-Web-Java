package com.kyra.cosmetics.service;

import com.kyra.cosmetics.exception.BadRequestException;
import com.kyra.cosmetics.exception.ResourceNotFoundException;
import com.kyra.cosmetics.model.*;
import com.kyra.cosmetics.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public Cart getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    public Cart addProduct(Long userId, Long productId, int quantity) {

        Cart cart = getCartByUser(userId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getStock() < quantity) {
            throw new BadRequestException("Not enough stock");
        }

        CartItem cartItem = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), productId)
                .orElse(null);

        if (cartItem == null) {
            cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .build();
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        cartItemRepository.save(cartItem);

        return cart;
    }

    public void removeProduct(Long userId, Long productId) {

        Cart cart = getCartByUser(userId);

        CartItem cartItem = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not in cart"));

        cartItemRepository.delete(cartItem);
    }
}
