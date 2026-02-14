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
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many CartItems -> 1 Cart
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnoreProperties("cartItems")
    private Cart cart;

    // Many CartItems -> 1 Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("category")
    private Product product;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}
