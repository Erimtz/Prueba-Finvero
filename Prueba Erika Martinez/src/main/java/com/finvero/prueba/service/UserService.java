package com.finvero.prueba.service;

import com.finvero.prueba.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
	void registerUser(UserEntity user);

	void deleteById(int id);

	Optional<UserEntity> findById(int id);

	List<UserEntity> findAll();

	boolean existsByUsername(String username);
}
