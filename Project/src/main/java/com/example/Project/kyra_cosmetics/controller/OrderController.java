package com.example.Project.controller;


import com.example.Project.model.Order;
import com.example.Project.model.OrderStatus;
import com.example.Project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}")
    public Order placeOrder(@PathVariable Long userId) {
        return orderService.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    @PatchMapping("/{orderId}/status")
    public Order updateStatus(@PathVariable Long orderId,
                              @RequestParam OrderStatus status) {
        return orderService.updateStatus(orderId, status);
    }
}
