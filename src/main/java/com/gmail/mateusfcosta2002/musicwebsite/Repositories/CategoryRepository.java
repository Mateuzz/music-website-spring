package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Category;

@org.springframework.stereotype.Repository
public interface CategoryRepository extends Repository<Category, Long> {
    Optional<Category> findById(Long id);
    void deleteById(Long id);

    Category save(Category category);
}
