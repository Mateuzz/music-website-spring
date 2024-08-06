package com.gmail.mateusfcosta2002.musicwebsite.Controllers;

import java.util.stream.Stream;

import org.springframework.http.HttpStatus; import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Author;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.AuthorDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers.AuthorMapper;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.AuthorRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.NotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

record AuthorPost(
    @Size(max = 150) @NotNull String name
) {}

record AuthorIndexResponse(
    Stream<AuthorDTO> authors,
    LinksDTO links
) {}

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    public AuthorController(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public AuthorIndexResponse index() {
        var authors = authorRepository
            .findStreamAllBy()
            .map(authorMapper::createDTO);

        return new AuthorIndexResponse(
            authors,
            authorMapper.linkDTOAggregate()
        );
    }

    @GetMapping("/{id}")
    public AuthorDTO show(@PathVariable Long id) throws NotFoundException {
        var author = authorRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Author with id " + id + " not found"));

        return authorMapper.createDTO(author);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO create(@Valid AuthorPost form) {
        var author = new Author(form.name());
        authorRepository.save(author);

        return authorMapper.createDTO(author);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public AuthorDTO delete(@PathVariable Long id) throws NotFoundException {
        var author = authorRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Author with id " + id + " not found"));

        authorRepository.delete(author);

        return authorMapper.createDTO(author);
    }

}
