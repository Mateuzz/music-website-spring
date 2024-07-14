package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Stream<Author> findStreamAllBy();
}
