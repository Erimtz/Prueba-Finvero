package com.finvero.prueba.service.impl;

import com.finvero.prueba.model.UserEntity;
import com.finvero.prueba.repo.UserRepository;
import com.finvero.prueba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;


	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<UserEntity> findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.findByUsername(username) != null;
	}
	@Override
	public void registerUser(UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
