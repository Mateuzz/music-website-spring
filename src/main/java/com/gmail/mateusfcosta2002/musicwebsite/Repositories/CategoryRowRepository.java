package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class CategoryRowRepository {
    public record CategoryRow(long id, int nesting, Long parentID, String name) {}

    private EntityManager manager;

    public CategoryRowRepository(EntityManager manager) {
        this.manager = manager;
    }

    @SuppressWarnings("unchecked")
    public List<CategoryRow> getAllCategories() {
        return manager
            .createNativeQuery("select id, nesting, parent_id as parentID, name from categories order by parentID NULLS FIRST", CategoryRow.class)
            .getResultList();
    }
}
