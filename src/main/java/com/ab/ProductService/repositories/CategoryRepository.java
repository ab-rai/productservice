package com.ab.ProductService.repositories;

import com.ab.ProductService.models.Category;
import com.ab.ProductService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);

}
