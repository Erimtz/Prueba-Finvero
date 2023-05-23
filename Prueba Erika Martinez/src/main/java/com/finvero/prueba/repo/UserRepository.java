package com.finvero.prueba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finvero.prueba.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUsername(String userName);

	@Query(value = "SELECT u.username FROM user_entity u \n" +
			"INNER JOIN order_entity oe ON u.id = oe.userid \n" +
			"WHERE oe.id = :orderId", nativeQuery = true)
	String getUsername(@Param("orderId") int orderId);
}
