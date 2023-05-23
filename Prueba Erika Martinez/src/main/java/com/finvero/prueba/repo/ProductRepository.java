package com.finvero.prueba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finvero.prueba.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
