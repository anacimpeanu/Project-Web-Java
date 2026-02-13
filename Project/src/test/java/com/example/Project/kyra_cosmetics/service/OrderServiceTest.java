package com.example.Project.kyra_cosmetics.service;

import com.example.Project.kyra_cosmetics.exception.BadRequestException;
import com.example.Project.kyra_cosmetics.exception.ResourceNotFoundException;
import com.example.Project.kyra_cosmetics.model.*;
import com.example.Project.kyra_cosmetics.repository.*;
import com.example.Project.kyra_cosmetics.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderItemRepository orderItemRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    OrderService orderService;

    @Test
    void placeOrder_cartMissing_throws() {
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.placeOrder(1L));
    }

    @Test
    void placeOrder_emptyCart_throws() {
        Cart cart = Cart.builder().id(1L).cartItems(new ArrayList<>()).build();
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));

        assertThrows(BadRequestException.class, () -> orderService.placeOrder(1L));
    }

    @Test
    void placeOrder_reducesStock_andSavesOrder() {
        Product p = Product.builder().id(10L).name("prod").price(2.0).stock(5).build();
        CartItem ci = CartItem.builder().id(3L).product(p).quantity(2).build();
        User user = User.builder().id(7L).build();
        Cart cart = Cart.builder().id(1L).user(user).cartItems(new ArrayList<>(List.of(ci))).build();

        when(cartRepository.findByUserId(7L)).thenReturn(Optional.of(cart));
        when(productRepository.save(any(Product.class))).thenAnswer(inv -> inv.getArgument(0));
        when(orderRepository.save(any(Order.class))).thenAnswer(inv -> {
            Order o = inv.getArgument(0);
            o.setId(55L);
            return o;
        });

        Order saved = orderService.placeOrder(7L);

        assertNotNull(saved.getId());
        // product stock decreased from 5 to 3
        assertEquals(3, p.getStock());
        verify(orderItemRepository).saveAll(anyList());
        verify(cartRepository).save(cart);
    }
}
