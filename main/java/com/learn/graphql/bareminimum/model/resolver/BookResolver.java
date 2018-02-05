package com.learn.graphql.bareminimum.model.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.learn.graphql.bareminimum.model.Author;
import com.learn.graphql.bareminimum.model.Book;
import com.learn.graphql.bareminimum.model.repository.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {
    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findOne(book.getAuthor().getId());
    }
}
