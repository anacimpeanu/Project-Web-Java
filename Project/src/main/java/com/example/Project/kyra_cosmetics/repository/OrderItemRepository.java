package com.example.Project.kyra_cosmetics.repository;


import com.example.Project.kyra_cosmetics.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
