package com.example.Project.kyra_cosmetics.repository;

import com.example.Project.model.Cart;
import com.example.Project.model.User;
import com.example.Project.repository.CartRepository;
import com.example.Project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findByUserId_work() {
        User u = User.builder().email("c@d.com").name("n").password("p").build();
        userRepository.save(u);

        Cart cart = Cart.builder().user(u).build();
        cartRepository.save(cart);

        Cart found = cartRepository.findByUserId(u.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getUser().getId()).isEqualTo(u.getId());
    }
}
