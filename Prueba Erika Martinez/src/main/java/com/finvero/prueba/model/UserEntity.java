package com.finvero.prueba.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_entity")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private String username;
	private String password;


	/**
	 * @param username
	 * @param password
	 */
	public UserEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
