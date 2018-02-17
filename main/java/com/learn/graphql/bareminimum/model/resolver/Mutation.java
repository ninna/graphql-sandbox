package com.learn.graphql.bareminimum.model.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.learn.graphql.bareminimum.model.Author;
import com.learn.graphql.bareminimum.model.Book;
import com.learn.graphql.bareminimum.model.error.BookNotFoundException;
import com.learn.graphql.bareminimum.model.repository.AuthorRepository;
import com.learn.graphql.bareminimum.model.repository.BookRepository;

public class Mutation implements GraphQLMutationResolver {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
        return author;
    }

    public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
        Book book = new Book();
        book.setAuthor(new Author(authorId));
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? 0 : pageCount);
        book.setTitle(title);

        bookRepository.save(book);
        return book;
    }

    public boolean deleteBook(Long bookId) {
        bookRepository.delete(bookId);
        return true;
    }

    public Book updateBookPageCount(Integer pageCount, Long id) {
        Book book = bookRepository.findOne(id);
        if (null == book) {
            throw new BookNotFoundException("Could not find book to update", id);
        }
        book.setPageCount(pageCount);
        bookRepository.save(book);
        return book;
    }
}
