package com.example.Project.kyra_cosmetics.controller;


import com.example.Project.kyra_cosmetics.model.User;
import com.example.Project.kyra_cosmetics.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Operations related to user management and authentication")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(
        summary = "Register a new user",
        description = "Creates a new user account with the provided details"
    )
    public User register(
            @Valid @RequestBody 
            @Parameter(description = "User registration details") 
            User user) {
        return userService.register(user);
    }

    @GetMapping
    @Operation(
        summary = "Get all users",
        description = "Retrieves a list of all registered users"
    )
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get user by ID",
        description = "Retrieves a specific user by their ID"
    )
    public User getById(
            @PathVariable 
            @Parameter(description = "User ID", example = "1") 
            Long id) {
        return userService.findById(id);
    }
}
