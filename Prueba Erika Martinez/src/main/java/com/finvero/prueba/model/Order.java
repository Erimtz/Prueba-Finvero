package com.finvero.prueba.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_entity")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "userid")
	private UserEntity user;

	@Column(name = "date")
	private Date date;

	@Column(name = "status")
	private String status;

}
