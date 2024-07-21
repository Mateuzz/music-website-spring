package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import java.net.URI;

import org.jooq.Record;
import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Category;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.CategoryDTO;

import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Categories.CATEGORIES;

@Component
public class CategoryMapper extends EntityAggregateMapper {
    public CategoryMapper() {
        super(URI.create("authors"));
    }

    public CategoryDTO createDTO(Category category) {
        var parent_id = category.getParent() == null ? null : category.getParent().getId();

        return new CategoryDTO(
            parent_id,
            category.getId(),
            category.getNesting(),
            category.getName(),
            linkDTOEntity(category.getId())
        );
    }

    /**
     * @param record the Result row, should have CATEGORIES.{ID, PARENT_ID, NAME, NESTING} fetched.
     */
    public CategoryDTO createDTOFromRecord(Record record) {
        long id = record.get(CATEGORIES.ID);

        return new CategoryDTO(
            record.get(CATEGORIES.PARENT_ID),
            id,
            record.get(CATEGORIES.NESTING),
            record.get(CATEGORIES.NAME),
            linkDTOEntity(id)
        );
    }
}
