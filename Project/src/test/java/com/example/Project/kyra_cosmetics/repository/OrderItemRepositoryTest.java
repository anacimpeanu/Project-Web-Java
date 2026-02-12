package com.example.Project.kyra_cosmetics.repository;

import com.example.Project.model.OrderItem;
import com.example.Project.model.Order;
import com.example.Project.model.Product;
import com.example.Project.model.User;
import com.example.Project.repository.OrderItemRepository;
import com.example.Project.repository.OrderRepository;
import com.example.Project.repository.ProductRepository;
import com.example.Project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderItemRepositoryTest {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void save_and_findOrderItem() {
        User u = User.builder().email("a@a.com").name("n").password("p").build();
        userRepository.save(u);

        Order order = Order.builder().user(u).build();
        orderRepository.save(order);

        Product p = Product.builder().name("p").price(1.0).stock(5).build();
        productRepository.save(p);

        OrderItem oi = OrderItem.builder().order(order).product(p).quantity(2).priceAtPurchase(1.0).build();
        orderItemRepository.save(oi);

        assertThat(orderItemRepository.findById(oi.getId())).isPresent();
    }
}
