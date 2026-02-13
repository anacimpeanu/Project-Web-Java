package com.example.Project.kyra_cosmetics.service;

import com.example.Project.kyra_cosmetics.exception.BadRequestException;
import com.example.Project.kyra_cosmetics.exception.ResourceNotFoundException;
import com.example.Project.kyra_cosmetics.model.*;
import com.example.Project.kyra_cosmetics.repository.*;
import com.example.Project.kyra_cosmetics.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    CartItemRepository cartItemRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    CartService cartService;

    @Test
    void getCartByUser_missing_throws() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cartService.getCartByUser(1L));
    }

    @Test
    void addProduct_productMissing_throws() {
        Cart cart = Cart.builder().id(2L).build();
        when(cartRepository.findByUserId(4L)).thenReturn(Optional.of(cart));
        when(productRepository.findById(9L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cartService.addProduct(4L,9L,1));
    }

    @Test
    void addProduct_notEnoughStock_throws() {
        Product p = Product.builder().id(5L).stock(1).build();
        Cart cart = Cart.builder().id(2L).build();
        when(cartRepository.findByUserId(4L)).thenReturn(Optional.of(cart));
        when(productRepository.findById(5L)).thenReturn(Optional.of(p));

        assertThrows(BadRequestException.class, () -> cartService.addProduct(4L,5L,2));
    }

    @Test
    void addProduct_savesNewCartItem() {
        Product p = Product.builder().id(5L).stock(10).build();
        Cart cart = Cart.builder().id(2L).build();
        when(cartRepository.findByUserId(4L)).thenReturn(Optional.of(cart));
        when(productRepository.findById(5L)).thenReturn(Optional.of(p));
        when(cartItemRepository.findByCartIdAndProductId(2L,5L)).thenReturn(Optional.empty());
        when(cartItemRepository.save(any(CartItem.class))).thenAnswer(inv -> inv.getArgument(0));

        Cart result = cartService.addProduct(4L,5L,3);

        assertEquals(cart, result);
        verify(cartItemRepository).save(any(CartItem.class));
    }

    @Test
    void removeProduct_notInCart_throws() {
        Cart cart = Cart.builder().id(2L).build();
        when(cartRepository.findByUserId(4L)).thenReturn(Optional.of(cart));
        when(cartItemRepository.findByCartIdAndProductId(2L,8L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cartService.removeProduct(4L,8L));
    }

    @Test
    void removeProduct_deletesCartItem() {
        Cart cart = Cart.builder().id(2L).build();
        CartItem ci = CartItem.builder().id(11L).cart(cart).product(Product.builder().id(8L).build()).build();
        when(cartRepository.findByUserId(4L)).thenReturn(Optional.of(cart));
        when(cartItemRepository.findByCartIdAndProductId(2L,8L)).thenReturn(Optional.of(ci));

        cartService.removeProduct(4L,8L);

        verify(cartItemRepository).delete(ci);
    }
}
