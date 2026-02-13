package com.example.Project.kyra_cosmetics.repository;

import com.example.Project.kyra_cosmetics.model.Order;
import com.example.Project.kyra_cosmetics.model.User;
import com.example.Project.kyra_cosmetics.repository.OrderRepository;
import com.example.Project.kyra_cosmetics.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findByUserId_work() {
        User u = User.builder().email("u@v.com").name("n").password("p").build();
        userRepository.save(u);

        Order o = Order.builder().user(u).build();
        orderRepository.save(o);

        List<Order> list = orderRepository.findByUserId(u.getId());
        assertThat(list).hasSize(1);
        assertThat(list.get(0).getUser().getId()).isEqualTo(u.getId());
    }
}
