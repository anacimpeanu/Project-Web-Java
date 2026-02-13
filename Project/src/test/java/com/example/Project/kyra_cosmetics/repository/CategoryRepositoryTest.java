package com.example.Project.kyra_cosmetics.repository;

import com.example.Project.kyra_cosmetics.model.Category;
import com.example.Project.kyra_cosmetics.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void existsByName_work() {
        Category c = Category.builder().name("Beauty").build();
        categoryRepository.save(c);

        assertThat(categoryRepository.existsByName("Beauty")).isTrue();
    }
}
