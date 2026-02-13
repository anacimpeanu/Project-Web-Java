package com.example.Project.kyra_cosmetics.repository;

import com.example.Project.kyra_cosmetics.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
}
