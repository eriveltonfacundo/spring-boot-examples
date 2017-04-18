package com.example.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Document(indexName = "spring-test", type = "books")
public class Book {

	@Id
	private String id;
	private String title;
	private String author;
	private String releaseDate;

}