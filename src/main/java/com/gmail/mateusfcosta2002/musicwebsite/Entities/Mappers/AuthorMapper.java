package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import java.net.URI;

import org.jooq.Record;
import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Author;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.AuthorDTO;

import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Authors.AUTHORS;

@Component
public class AuthorMapper extends EntityAggregateMapper {
    public AuthorMapper() {
        super(URI.create("authors"));
    }

    public AuthorDTO createDTO(Author author) {
        return new AuthorDTO(
            linkDTOEntity(author.getId()),
            author.getId(),
            author.getName()
        );
    }

    /**
     * @param record the Result row, should have AUTHORS.{ID, NAME} fetched.
     */
    public AuthorDTO createDTOFromRecord(Record record) {
        long id = record.get(AUTHORS.ID);

        return new AuthorDTO(
            linkDTOEntity(id),
            id, 
            record.get(AUTHORS.NAME)
        );
    }
}
