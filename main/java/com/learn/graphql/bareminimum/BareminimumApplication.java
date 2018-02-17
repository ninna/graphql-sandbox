package com.learn.graphql.bareminimum;

import com.learn.graphql.bareminimum.model.Author;
import com.learn.graphql.bareminimum.model.Book;
import com.learn.graphql.bareminimum.model.error.GraphQLErrorAdapter;
import com.learn.graphql.bareminimum.model.repository.AuthorRepository;
import com.learn.graphql.bareminimum.model.repository.BookRepository;
import com.learn.graphql.bareminimum.model.resolver.BookResolver;
import com.learn.graphql.bareminimum.model.resolver.Mutation;
import com.learn.graphql.bareminimum.model.resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class BareminimumApplication {

    public static void main(String[] args) {
        SpringApplication.run(BareminimumApplication.class, args);
    }

    @Bean public BookResolver authorResolver(AuthorRepository authorRepository) {
        return new BookResolver(authorRepository);
    }

    @Bean public Query query(AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Query(authorRepository, bookRepository);
    }

    @Bean public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Mutation(authorRepository, bookRepository);
    }

    @Bean public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
        return (args) -> {
            Author author = new Author("Herbert", "Schildt");
            Book book = new Book("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, author);
            authorRepository.save(author);
            bookRepository.save(book);

            Author author1 = authorRepository.save(new Author("Nora", "Meils"));
            Book book2 = new Book("Java2: A Beginner's Guide, Sixth Edition", "0071809252", 728, author1);
            bookRepository.save(book2);
        };
    }
    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {

            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());
                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());
                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };

    }
}
