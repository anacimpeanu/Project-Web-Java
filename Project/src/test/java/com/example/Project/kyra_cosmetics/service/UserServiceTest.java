package com.example.Project.kyra_cosmetics.service;

import com.example.Project.exception.BadRequestException;
import com.example.Project.model.Cart;
import com.example.Project.model.User;
import com.example.Project.repository.CartRepository;
import com.example.Project.repository.UserRepository;
import com.example.Project.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    CartRepository cartRepository;

    @InjectMocks
    UserService userService;

    @Test
    void register_existingEmail_throwsBadRequest() {
        User user = User.builder().email("a@b.com").name("n").password("p").build();
        when(userRepository.existsByEmail("a@b.com")).thenReturn(true);

        assertThrows(BadRequestException.class, () -> userService.register(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    void register_createsUserAndCart() {
        User user = User.builder().email("x@y.com").name("n").password("p").build();
        when(userRepository.existsByEmail("x@y.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId(1L);
            return u;
        });
        when(cartRepository.save(any(Cart.class))).thenAnswer(inv -> inv.getArgument(0));

        User saved = userService.register(user);

        assertNotNull(saved.getId());
        ArgumentCaptor<Cart> captor = ArgumentCaptor.forClass(Cart.class);
        verify(cartRepository).save(captor.capture());
        Cart savedCart = captor.getValue();
        assertNotNull(savedCart.getUser());
        assertEquals(saved.getId(), savedCart.getUser().getId());
    }
}
