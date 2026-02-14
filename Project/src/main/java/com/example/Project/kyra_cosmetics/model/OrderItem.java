package com.example.Project.kyra_cosmetics.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"orderItems", "user"})
    private Order order;

    // Many OrderItems -> 1 Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("category")
    private Product product;

    @Min(value = 1)
    private int quantity;

    // Preț salvat la momentul cumpărării
    private double priceAtPurchase;
}
