package com.example.spring.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.spring.model.Cachable;
import com.example.spring.model.User;

public class UserMapRepository implements CRUDRepository<User> {
	
	@Autowired
	private Map<String, Cachable> userRedisMap;

	@Override
	public void put(User user) {
		userRedisMap.put(user.getKey(), user);
	}

	@Override
	public void delete(User key) {
		userRedisMap.remove(key.getKey());
	}

	@Override
	public User get(User key) {
		return (User) userRedisMap.get(key.getKey());
	}
}
