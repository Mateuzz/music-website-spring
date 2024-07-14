package com.gmail.mateusfcosta2002.musicwebsite.Services;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Author;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.AuthorDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinkDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Util.LinksDTOUtils;

@Component
public class AuthorService {
    private LinksDTOUtils linksDTOUtils;

    public AuthorService(LinksDTOUtils linksDTOUtils) {
        this.linksDTOUtils = linksDTOUtils;
    }

    public LinkDTO getCanonicalLink(long id) {
        return linksDTOUtils.linkDTO("authors/" + id, List.of("GET", "DELETE"));
    }

    public LinkDTO getRootLink() {
        return linksDTOUtils.linkDTO("authors", List.of("GET", "POST"));
    }

    public AuthorDTO createDTO(Author author) {
        return new AuthorDTO(
            LinksDTO.withCanonical(getCanonicalLink(author.getId())),
            author.getId(),
            author.getName()
        );
    }
}
