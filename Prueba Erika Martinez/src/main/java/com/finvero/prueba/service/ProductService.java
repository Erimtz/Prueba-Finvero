package com.finvero.prueba.service;

import com.finvero.prueba.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
	Product save(Product product);

	void deleteById(int id);

	Optional<Product> findById(int id);

	List<Product> findAll();
}
