package com.alten.backend.Repositories;

import com.alten.backend.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    Product findBycode(String code);
}
