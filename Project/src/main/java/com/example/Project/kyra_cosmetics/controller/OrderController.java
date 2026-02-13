package com.example.Project.kyra_cosmetics.controller;


import com.example.Project.kyra_cosmetics.model.Order;
import com.example.Project.kyra_cosmetics.model.OrderStatus;
import com.example.Project.kyra_cosmetics.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order Management", description = "Operations for managing customer orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}")
    @Operation(
        summary = "Place a new order",
        description = "Creates a new order from the user's current cart"
    )
    public Order placeOrder(
            @PathVariable 
            @Parameter(description = "User ID", example = "1") 
            Long userId) {
        return orderService.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    @Operation(
        summary = "Get user's orders",
        description = "Retrieves all orders placed by a specific user"
    )
    public List<Order> getUserOrders(
            @PathVariable 
            @Parameter(description = "User ID", example = "1") 
            Long userId) {
        return orderService.getUserOrders(userId);
    }

    @PatchMapping("/{orderId}/status")
    @Operation(
        summary = "Update order status",
        description = "Updates the status of an existing order (admin operation)"
    )
    public Order updateStatus(
            @PathVariable 
            @Parameter(description = "Order ID", example = "1") 
            Long orderId,
            @RequestParam 
            @Parameter(description = "New order status") 
            OrderStatus status) {
        return orderService.updateStatus(orderId, status);
    }
}
