package com.example.Project.kyra_cosmetics.repository;

import com.example.Project.model.User;
import com.example.Project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void existsByEmail_and_findByEmail_work() {
        User u = User.builder().email("t@u.com").name("n").password("p").build();
        userRepository.save(u);

        boolean exists = userRepository.existsByEmail("t@u.com");
        assertThat(exists).isTrue();

        User found = userRepository.findByEmail("t@u.com").orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("t@u.com");
    }
}
