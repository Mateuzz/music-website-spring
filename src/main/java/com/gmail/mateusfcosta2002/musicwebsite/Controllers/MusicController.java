package com.gmail.mateusfcosta2002.musicwebsite.Controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.MusicDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.MusicRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Services.MusicService;
import com.gmail.mateusfcosta2002.musicwebsite.Services.MusicService.MusicPost;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.NotFoundException;

import jakarta.validation.Valid;

record MusicIndexResponse(
    Stream<MusicDTO> musics,
    LinksDTO links
) implements Serializable {}

@RestController
@RequestMapping("/musics")
public class MusicController {
    private MusicRepository repository;
    private MusicService musicService;

    public MusicController(MusicRepository repository, MusicService musicService) {
        this.repository = repository;
        this.musicService = musicService;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public MusicIndexResponse index() {
        var musics = repository
            .findAllWithAuthorCategoryTagsBy()
            .map(musicService::createDTO);

        return new MusicIndexResponse(
            musics,
            LinksDTO.withCanonical(musicService.getRootLink()));
    }

    @GetMapping("/{id}")
    public MusicDTO show(@PathVariable long id) throws NotFoundException {
        var music = repository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Music with id " + id + " not found"));

        return musicService.createDTO(music);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicDTO create(@Valid MusicPost form) throws Exception {
        var music = musicService.create(form);
        return musicService.createDTO(music);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) throws NotFoundException, IOException {
        musicService.delete(id);
    }
}
