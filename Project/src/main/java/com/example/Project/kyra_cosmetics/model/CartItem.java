package com.example.Project.kyra_cosmetics.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many CartItems -> 1 Cart
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // Many CartItems -> 1 Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}
