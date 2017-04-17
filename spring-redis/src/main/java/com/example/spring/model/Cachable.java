package com.example.spring.model;

import java.io.Serializable;

public interface Cachable extends Serializable {

	String getKey();

	String getObjectKey();
}