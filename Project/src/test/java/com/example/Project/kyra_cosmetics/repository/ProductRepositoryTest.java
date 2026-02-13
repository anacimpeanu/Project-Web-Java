package com.example.Project.kyra_cosmetics.repository;

import com.example.Project.kyra_cosmetics.model.Category;
import com.example.Project.kyra_cosmetics.model.Product;
import com.example.Project.kyra_cosmetics.repository.CategoryRepository;
import com.example.Project.kyra_cosmetics.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void findByCategoryId_and_findByNameContainingIgnoreCase_work() {
        Category cat = Category.builder().name("Skin").build();
        categoryRepository.save(cat);

        Product p1 = Product.builder().name("Face Cream").price(5.0).stock(10).category(cat).build();
        Product p2 = Product.builder().name("Hand Cream").price(3.0).stock(4).category(cat).build();
        productRepository.saveAll(List.of(p1, p2));

        List<Product> byCat = productRepository.findByCategoryId(cat.getId());
        assertThat(byCat).hasSize(2);

        List<Product> byName = productRepository.findByNameContainingIgnoreCase("cream");
        assertThat(byName).hasSize(2);
    }
}
