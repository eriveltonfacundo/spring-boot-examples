package com.example.spring.config.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of="token")
@Builder @NoArgsConstructor @AllArgsConstructor
public class AuthenticationResponse{
	private String token;
}
