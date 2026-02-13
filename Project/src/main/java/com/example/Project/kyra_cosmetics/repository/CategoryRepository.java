package com.example.Project.kyra_cosmetics.repository;


import com.example.Project.kyra_cosmetics.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}
