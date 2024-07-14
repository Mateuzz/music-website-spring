package com.gmail.mateusfcosta2002.musicwebsite.Services;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.imageio.plugins.tiff.ExifTIFFTagSet;

import org.aspectj.weaver.Iterators;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Music;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Tag;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinkDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.MusicDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.TagDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Util.LinksDTOUtils;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.AuthorRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.CategoryRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.MusicRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.TagRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.NotFoundException;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Component
public class MusicService {
    private LinksDTOUtils linksDTOUtils;
    private AuthorService authorService;
    private CategoryService categoryService;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private StorageService storageService;
    private MusicRepository musicRepository;
    private TagRepository tagRepository;
    private WebProperties webProperties;

    public MusicService(LinksDTOUtils linksDTOUtils, AuthorService authorService, CategoryService categoryService,
            AuthorRepository authorRepository, CategoryRepository categoryRepository, StorageService storageService,
            MusicRepository musicRepository, TagRepository tagRepository, WebProperties webProperties) {
        this.linksDTOUtils = linksDTOUtils;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.storageService = storageService;
        this.musicRepository = musicRepository;
        this.tagRepository = tagRepository;
        this.webProperties = webProperties;
    }

    public record MusicPost(
            @Size(max = 150) @NotNull String name,
            long authorID,
            long categoryID,
            int durationInSeconds,
            @NotNull MultipartFile file,
            String[] tags) implements Serializable {
    }

    public LinkDTO getCanonicalLink(long id) {
        return linksDTOUtils.linkDTO("musics/" + id, List.of("GET", "DELETE"));
    }

    public LinkDTO getRootLink() {
        return linksDTOUtils.linkDTO("musics", List.of("GET", "POST"));
    }

    public Music create(MusicPost post) throws IOException, NotFoundException {
        var author = authorRepository
                .findById(post.authorID())
                .orElseThrow(() -> new NotFoundException("Author with id " + post.authorID() + " not found"));

        var category = categoryRepository
                .findById(post.categoryID())
                .orElseThrow(() -> new NotFoundException("Category with id " + post.categoryID() + " not found"));

        Set<Tag> tags = new HashSet<>();

        if (post.tags() != null && post.tags().length > 0) {
            var existingTags = tagRepository.findAllById(Arrays.asList(post.tags()));
            for (var tagName : post.tags()) {
                tags.add(new Tag(tagName));
            }

            tags.removeAll(existingTags);
            tagRepository.saveAll(tags);
            tags.addAll(existingTags);
        }

        var filepath = storageService.uploadFileRandom(post.file(), Path.of("musics"));

        var music = new Music(post.name(), category, 100, filepath.toString(), author, tags);

        return musicRepository.save(music);
    }

    public void delete(long id) throws NotFoundException, IOException {
        var music = musicRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Music with id " + id + " not found"));

        storageService.deleteFile(Path.of(music.getFilepath()));
        musicRepository.delete(music);
    }

    public MusicDTO createDTO(Music music) {
        var htmlFilepath = webProperties.getFilepathRelativeToPublic(Path.of(music.getFilepath()));
        var tagsDTO = new TagDTO[music.getTags().size()];

        int i = 0;

        for (var tag: music.getTags()) {
            tagsDTO[i++] = TagService.createDTO(tag);
        }

        return new MusicDTO(
                music.getId(),
                Arrays.asList(tagsDTO),
                music.getName(),
                authorService.createDTO(music.getAuthor()),
                categoryService.createDTO(music.getCategory()),
                htmlFilepath,
                LinksDTO.withCanonical(getCanonicalLink(music.getId())));
    }
}
