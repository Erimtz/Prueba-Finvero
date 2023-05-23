package com.finvero.prueba.controller;


import com.finvero.prueba.model.UserEntity;
import com.finvero.prueba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.finvero.prueba.model.JwtRequest;
import com.finvero.prueba.model.JwtResponse;
import com.finvero.prueba.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@PostMapping("users/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("users/register")
	public ResponseEntity<?> registerUser(@RequestBody JwtRequest user) {
		if (userService.existsByUsername(user.getUsername())) {
			return ResponseEntity.badRequest().body("Username already exists");
		}

		UserEntity userEntity = new UserEntity();
		userEntity.setPassword(user.getPassword());
		userEntity.setUsername(user.getUsername());
		userService.registerUser(userEntity);

		return ResponseEntity.ok("User registered successfully");
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "userId") int userId, @RequestBody JwtRequest user) {
		if (!userService.findById(userId).isPresent()) {
			return ResponseEntity.badRequest().body("User does not exists");
		}

		UserEntity userEntity = new UserEntity();
		userEntity.setPassword(user.getPassword());
		userEntity.setUsername(user.getUsername());
		userService.registerUser(userEntity);

		return ResponseEntity.ok("User updated successfully");
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") int userId) {
		if (!userService.findById(userId).isPresent()) {
			return ResponseEntity.badRequest().body("User does not exists");
		}

		userService.deleteById(userId);

		return ResponseEntity.ok("User deleted successfully");
	}
}