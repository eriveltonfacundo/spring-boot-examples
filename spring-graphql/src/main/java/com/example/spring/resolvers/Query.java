package com.example.spring.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.spring.models.Author;
import com.example.spring.models.Book;
import com.example.spring.repository.AuthorRepository;
import com.example.spring.repository.BookRepository;

public class Query implements GraphQLQueryResolver {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository; this.bookRepository = bookRepository;
    }
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
    public long countBooks() {
        return bookRepository.count();
    } public long countAuthors() {
        return authorRepository.count();
    }
}