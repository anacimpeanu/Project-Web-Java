package com.example.Project.service;


import com.kyra.cosmetics.exception.BadRequestException;
import com.kyra.cosmetics.exception.ResourceNotFoundException;
import com.kyra.cosmetics.model.Cart;
import com.kyra.cosmetics.model.Role;
import com.kyra.cosmetics.model.User;
import com.kyra.cosmetics.repository.CartRepository;
import com.kyra.cosmetics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        user.setRole(Role.CUSTOMER);

        User savedUser = userRepository.save(user);

        // Creează automat cart pentru user
        Cart cart = Cart.builder()
                .user(savedUser)
                .build();

        cartRepository.save(cart);

        return savedUser;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
