package com.gmail.mateusfcosta2002.musicwebsite.Controllers;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra.CommonResponses.Pagination;
import com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra.MusicRequests.MusicPost;
import com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra.MusicRequests.MusicSearchRequest;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Music;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Tag;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.MusicDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers.MusicMapper;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.AuthorRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.CategoryRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.CustomMusicRepository.MusicOrder;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.MusicRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.TagRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Services.PaginationService;
import com.gmail.mateusfcosta2002.musicwebsite.Services.StorageService;
import com.gmail.mateusfcosta2002.musicwebsite.Util.FileDetector;
import com.gmail.mateusfcosta2002.musicwebsite.Util.FileUtils;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

record MusicPageResponse(Pagination<MusicDTO> musics, LinksDTO links) {
}

@RestController
@RequestMapping("/musics")
public class MusicController {
    private final static int DEFAULT_PAGE_SIZE = 50;
    private final static MusicOrder DEFAULT_MUSIC_ORDER = MusicOrder.latest;
    private final Path MUSICS_PATH = Path.of("musics");

    private MusicRepository musicRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private TagRepository tagRepository;

    private StorageService storageService;
    private PaginationService paginationService;

    private MusicMapper musicMapper;
    private FileDetector fileDetector;

    private ApplicationContext ctx;

    public MusicController(MusicRepository musicRepository, AuthorRepository authorRepository,
			CategoryRepository categoryRepository, TagRepository tagRepository, StorageService storageService,
			PaginationService paginationService, MusicMapper musicMapper, FileDetector fileDetector,
			ApplicationContext ctx) {
		this.musicRepository = musicRepository;
		this.authorRepository = authorRepository;
		this.categoryRepository = categoryRepository;
		this.tagRepository = tagRepository;
		this.storageService = storageService;
		this.paginationService = paginationService;
		this.musicMapper = musicMapper;
		this.fileDetector = fileDetector;
		this.ctx = ctx;
	}

	@GetMapping
    public MusicPageResponse index(MusicSearchRequest form, HttpServletRequest req) {
        int pageSize = form.pageSize() != null ? form.pageSize() : DEFAULT_PAGE_SIZE;
        MusicOrder order = form.order() != null ? form.order() : DEFAULT_MUSIC_ORDER;

        var musicPageRecords = musicRepository.search(form.search(), pageSize, form.page(), order, form.authorID(), form.categoryID(), form.tags());

        var musicDTOPageResult = musicMapper.createPageDTOFromPageRecords(musicPageRecords);

        var musicDTOPagination = paginationService.build(musicDTOPageResult, req);

        return new MusicPageResponse(
                musicDTOPagination,
                musicMapper.linkDTOAggregate());
    }

    @GetMapping("/{id}")
    public MusicDTO show(@PathVariable long id) throws NotFoundException {
        var music = musicRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Music with id " + id + " not found"));

        return musicMapper.createDTO(music);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> create(@Valid MusicPost form) throws Exception {
        var author = authorRepository
                .findById(form.authorID())
                .orElseThrow(() -> new NotFoundException("Author with id " + form.authorID() + " not found"));

        var category = categoryRepository
                .findById(form.categoryID())
                .orElseThrow(() -> new NotFoundException("Category with id " + form.categoryID() + " not found"));

        String musicFilepath = null;
        String originalFilename = form.file().getOriginalFilename();

        Path uploadedFilepath = storageService.uploadFileRandomName(form.file(), MUSICS_PATH, originalFilename);
        String uploadedFilepathString = uploadedFilepath.toString();

        try (var inputStream = new BufferedInputStream(new FileInputStream(uploadedFilepathString))) {
            var mediaType = fileDetector.getMediaType(inputStream, originalFilename);
            var fileExtension = FileDetector.getAudioExtension(mediaType);

            if (fileExtension == null) {
                var msg = ctx.getMessage("error.mime.audio",
                        new Object[] { originalFilename, mediaType.toString(),
                                FileDetector.getAcceptedAudioMimeTypes() },
                        LocaleContextHolder.getLocale());

                return ResponseEntity
                        .unprocessableEntity()
                        .body(msg);
            }

            musicFilepath = FileUtils.replaceExtension(uploadedFilepathString, fileExtension);

            if (!musicFilepath.equals(uploadedFilepathString))
                storageService.move(uploadedFilepath, Path.of(musicFilepath));
        }

        Set<Tag> tags = new HashSet<>();

        if (form.tags() != null && form.tags().length > 0) {
            var existingTags = tagRepository.findAllById(Arrays.asList(form.tags()));
            for (var tagName : form.tags()) {
                tags.add(new Tag(tagName));
            }

            tags.removeAll(existingTags);
            tagRepository.saveAll(tags);
            tags.addAll(existingTags);
        }

        var music = new Music(form.name(), category, musicFilepath, author, tags);

        musicRepository.save(music);

        return ResponseEntity.ok(musicMapper.createDTO(music));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public MusicDTO delete(@PathVariable long id) throws NotFoundException, IOException {
        var music = musicRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Music with id " + id + " not found"));

        musicRepository.delete(music);
        storageService.deleteIfExists(Path.of(music.getFilepath()));

        return musicMapper.createDTO(music);
    }
}
