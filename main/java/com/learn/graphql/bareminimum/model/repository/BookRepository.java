package com.learn.graphql.bareminimum.model.repository;

import com.learn.graphql.bareminimum.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
