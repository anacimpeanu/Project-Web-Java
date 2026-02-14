package com.example.Project.kyra_cosmetics.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1 Cart -> 1 User
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"cart", "orders"})
    private User user;

    // 1 Cart -> Many CartItems
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cart")
    private List<CartItem> cartItems;
}
