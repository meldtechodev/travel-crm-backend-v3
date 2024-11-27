package com.MotherSon.CRM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsById(int i);
	
	boolean existsByEmail(String email);  // Check if email already exists
    Optional<User> findByEmail(String email);
 
}
