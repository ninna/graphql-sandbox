package com.learn.graphql.bareminimum.model.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.learn.graphql.bareminimum.model.repository.AuthorRepository;
import com.learn.graphql.bareminimum.model.repository.BookRepository;

public class Mutation implements GraphQLMutationResolver {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
}
