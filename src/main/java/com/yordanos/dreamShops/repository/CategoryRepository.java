package com.yordanos.dreamShops.repository;

import com.yordanos.dreamShops.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
    boolean existsByName(String name);
}
