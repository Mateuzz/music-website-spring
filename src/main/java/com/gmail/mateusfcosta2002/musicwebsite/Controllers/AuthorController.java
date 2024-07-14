package com.gmail.mateusfcosta2002.musicwebsite.Controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
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
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.AuthorRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.NotFoundException;
import com.gmail.mateusfcosta2002.musicwebsite.Services.AuthorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

record AuthorPost(
    @Size(max = 150) @NotNull String name
) {}

record AuthorIndexResponse(
    List<AuthorDTO> authors,
    LinksDTO links
) {}

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private AuthorRepository authorRepository;
    private AuthorService authorService;

    public AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @GetMapping
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public AuthorIndexResponse index() {
        var authors = authorRepository
            .findStreamAllBy()
            .map(authorService::createDTO)
            .toList();

        return new AuthorIndexResponse(
            authors,
            LinksDTO.withCanonical(authorService.getRootLink())
        );
    }

    @GetMapping("/{id}")
    public AuthorDTO show(@PathVariable Long id) throws NotFoundException {
        var author = authorRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Author with id " + id + " not found"));

        return authorService.createDTO(author);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO create(@Valid AuthorPost form) {
        var author = new Author(form.name());
        authorRepository.save(author);

        return authorService.createDTO(author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }

    @GetMapping("/musics")
    public void getMusics() {

    }
}
