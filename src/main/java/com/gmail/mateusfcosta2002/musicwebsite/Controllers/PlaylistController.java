package com.gmail.mateusfcosta2002.musicwebsite.Controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra.CommonResponses.MessageResponse;
import com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra.CommonResponses.Pagination;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Playlist;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.MusicDTOPlaylistItem;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.PlaylistDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers.PlaylistMapper;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.MusicRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.PlaylistRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.UserRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.CustomMusicRepository.MusicPlaylistOrder;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Entities.AppUserDetails;
import com.gmail.mateusfcosta2002.musicwebsite.Services.PaginationService;
import com.gmail.mateusfcosta2002.musicwebsite.Services.StorageService;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

record IndexResponse(Stream<PlaylistDTO> playlists, LinksDTO links) {
}

record IndexMusicsResponse(Pagination<MusicDTOPlaylistItem> musics, LinksDTO links) {
}

record MusicPlaylistPost(long musicID) {
}

record PlaylistMusicsSearchRequest(
    String search,
    MusicPlaylistOrder order,
    @Size(min = 1) int page,
    @Size(max = 100) Integer pageSize
) {
}


record PlaylistPost(
        @NotNull @Size(max = 150) String name,
        MultipartFile backgroundImage) {
}

@RequestMapping("/playlists")
@RestController
public class PlaylistController {
	private static final Path PLAYLIST_BACKGROUND_IMAGES_PATH = Path.of("playlists", "background_images");
    
    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;
    private MusicRepository musicRepository;;

    private StorageService storageService;
    private PaginationService paginationService;

    private PlaylistMapper playlistMapper;

    public PlaylistController(PlaylistRepository playlistRepository, UserRepository userRepository,
			MusicRepository musicRepository, StorageService storageService, PaginationService paginationService,
			PlaylistMapper playlistMapper) {
		this.playlistRepository = playlistRepository;
		this.userRepository = userRepository;
		this.musicRepository = musicRepository;
		this.storageService = storageService;
		this.paginationService = paginationService;
		this.playlistMapper = playlistMapper;
	}

    @Transactional(readOnly = true)
    @GetMapping
    public IndexResponse index() {
        var playlists = playlistRepository
                .findAllWithUserBy()
                .stream()
                .map(playlistMapper::createDTO);

        return new IndexResponse(
                playlists,
                playlistMapper.linkDTOAggregate()
        );
    }

    @GetMapping("/{id}")
    public PlaylistDTO show(@PathVariable long id) throws NotFoundException {
        var playlist = playlistRepository
                .findWithUserById(id)
                .orElseThrow(() -> new NotFoundException("Playlist with id " + id + " not found"));

        return playlistMapper.createDTO(playlist);
    }

    // TODO add true user
    @PostMapping
    public PlaylistDTO create(PlaylistPost form, @AuthenticationPrincipal AppUserDetails principal)
            throws NotFoundException, IOException {
        var user = userRepository.getReferenceById(1L);

        var playlist = new Playlist(form.name(), user);

        if (form.backgroundImage() != null) {
            try (var input = form.backgroundImage().getInputStream()) {
                var filepath = storageService.uploadFileRandomName(input, PLAYLIST_BACKGROUND_IMAGES_PATH, form.backgroundImage().getOriginalFilename());
                playlist.setUriImage(filepath.toString());
            }
        }

        playlistRepository.save(playlist);

        return playlistMapper.createDTO(playlist);
    }

    // TODO add true user
    @PutMapping("/{id}")
    public PlaylistDTO put(
            PlaylistPost form,
            @AuthenticationPrincipal AppUserDetails principal,
            @PathVariable long id) throws NotFoundException, IOException {

        var playlist = playlistRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Playlist with id " + id + " not found"));

        playlist.setName(form.name());

        if (form.backgroundImage() != null) {
            try (var input = form.backgroundImage().getInputStream()) {
                storageService.deleteIfExists(Path.of(playlist.getUriImage()));
                var filepath = storageService.uploadFileRandomName(input, PLAYLIST_BACKGROUND_IMAGES_PATH, form.backgroundImage().getOriginalFilename());
                playlist.setUriImage(filepath.toString());
            }
        }

        playlistRepository.save(playlist);

        return playlistMapper.createDTO(playlist);
    }

    @PostMapping("/{id}/musics")
    public ResponseEntity<Object> addMusic(MusicPlaylistPost form, @PathVariable long id) {
        try {
            playlistRepository.addMusicById(id, form.musicID());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .unprocessableEntity()
                    .body(new MessageResponse("Trying to add music already in playlist or non existent music"));
        }

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/musics")
    public IndexMusicsResponse  indexMusics(PlaylistMusicsSearchRequest form, @PathVariable long id, HttpServletRequest req) {
        int pageSize = form.pageSize() != null ? form.pageSize() : 100;
        var order = form.order() != null ? form.order() : MusicPlaylistOrder.latest;

        var musicsResult = musicRepository.searchByCategory(form.search(), pageSize, form.page(), order, id);

        var musicsPageDTO = playlistMapper.createPageDTOFromPageRecordsPlaylistItem(musicsResult, id);

        var musicsDTOPagination = paginationService.build(musicsPageDTO, req);

        return new IndexMusicsResponse(
            musicsDTOPagination,
            playlistMapper.linkDTOEntityMusicItemsAggregate(id)
        );
    }

    @DeleteMapping("/{id}/musics")
    public void removeMusic(MusicPlaylistPost form, @PathVariable long id) {
        playlistRepository.removeMusicById(id, form.musicID());
    }

    @DeleteMapping("/{id}")
    public PlaylistDTO delete(@PathVariable long id) throws NotFoundException {
        var playlist = playlistRepository
                .findWithUserById(id)
                .orElseThrow(() -> new NotFoundException("Playlist with id " + id + " not found"));

        playlistRepository.delete(playlist);

        return playlistMapper.createDTO(playlist);
    }
}
