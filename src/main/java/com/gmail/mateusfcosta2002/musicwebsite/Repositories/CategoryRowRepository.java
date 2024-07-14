package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import java.util.List;

import jakarta.persistence.EntityManager;

@org.springframework.stereotype.Repository
public class CategoryRowRepository {
    public record CategoryRow(long id, int nesting, Long parent_id, String name) {}

    private EntityManager manager;

    public CategoryRowRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<CategoryRow> getAllCategories() {
        return manager
            .createNativeQuery("select id, nesting, parent_id, name from categories order by parent_id NULLS FIRST", CategoryRow.class)
            .getResultList();
    }
}
