package com.learn.graphql.bareminimum.model.repository;

import com.learn.graphql.bareminimum.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository  extends CrudRepository<Author, Long> {}
