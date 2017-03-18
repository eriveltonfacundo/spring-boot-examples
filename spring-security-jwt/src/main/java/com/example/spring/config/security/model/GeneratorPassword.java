package com.example.spring.config.security.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratorPassword {
	public static void main(String[] args) {

		String password = "admin";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);
	}
}
