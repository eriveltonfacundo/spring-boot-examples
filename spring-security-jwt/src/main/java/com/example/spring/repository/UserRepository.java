package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.spring.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
	
	 User findByUsername(String username);
}
