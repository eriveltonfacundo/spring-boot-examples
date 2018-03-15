package com.example.spring.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.spring.models.Author;
import com.example.spring.models.Book;
import com.example.spring.repository.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {
    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Author getAuthor(Book book) {
        return authorRepository.findById(book.getAuthor().getId()).orElse(null);
    }
}
















