package com.example.Project.kyra_cosmetics.repository;

import com.example.Project.model.Cart;
import com.example.Project.model.CartItem;
import com.example.Project.model.Product;
import com.example.Project.model.User;
import com.example.Project.repository.CartItemRepository;
import com.example.Project.repository.CartRepository;
import com.example.Project.repository.ProductRepository;
import com.example.Project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CartItemRepositoryTest {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findByCartIdAndProductId_work() {
        User u = User.builder().email("x@y.com").name("n").password("p").build();
        userRepository.save(u);

        Cart cart = Cart.builder().user(u).build();
        cartRepository.save(cart);

        Product p = Product.builder().name("prod").price(1.0).stock(5).build();
        productRepository.save(p);

        CartItem ci = CartItem.builder().cart(cart).product(p).quantity(2).build();
        cartItemRepository.save(ci);

        CartItem found = cartItemRepository.findByCartIdAndProductId(cart.getId(), p.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getQuantity()).isEqualTo(2);
    }
}
