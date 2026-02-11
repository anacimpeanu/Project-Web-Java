package com.example.Project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many OrderItems -> 1 Order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Many OrderItems -> 1 Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Min(value = 1)
    private int quantity;

    // Preț salvat la momentul cumpărării
    private double priceAtPurchase;
}
