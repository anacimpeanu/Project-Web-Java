package com.example.Project.kyra_cosmetics.service;


import com.example.Project.kyra_cosmetics.exception.BadRequestException;
import com.example.Project.kyra_cosmetics.exception.ResourceNotFoundException;
import com.example.Project.kyra_cosmetics.model.Cart;
import com.example.Project.kyra_cosmetics.model.Role;
import com.example.Project.kyra_cosmetics.model.User;
import com.example.Project.kyra_cosmetics.repository.CartRepository;
import com.example.Project.kyra_cosmetics.repository.UserRepository;
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
