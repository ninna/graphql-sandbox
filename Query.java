package com.learn.graphql.bareminimum.model.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.learn.graphql.bareminimum.model.Author;
import com.learn.graphql.bareminimum.model.Book;
import com.learn.graphql.bareminimum.model.repository.AuthorRepository;
import com.learn.graphql.bareminimum.model.repository.BookRepository;

public class Query implements GraphQLQueryResolver {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countBooks() {
        return bookRepository.count();
    }

    public long countAuthors() {
        return authorRepository.count();
    }
}
