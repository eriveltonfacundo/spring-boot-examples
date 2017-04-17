package com.example.spring.repository;

import com.example.spring.model.Cachable;

public interface CRUDRepository<T extends Cachable> {

	void put(T obj);

	T get(T key);

	void delete(T key);
}
