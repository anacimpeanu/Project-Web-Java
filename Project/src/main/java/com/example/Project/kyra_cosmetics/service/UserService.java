package com.example.Project.service;


import com.example.Project.exception.BadRequestException;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.model.Cart;
import com.example.Project.model.Role;
import com.example.Project.model.User;
import com.example.Project.repository.CartRepository;
import com.example.Project.repository.UserRepository;
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
