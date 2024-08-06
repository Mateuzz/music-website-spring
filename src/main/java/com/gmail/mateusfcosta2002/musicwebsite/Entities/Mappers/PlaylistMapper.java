package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jooq.Result;
import org.jooq.Record;
import org.springframework.stereotype.Component;
import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Playlist;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.MusicDTOPlaylistItem;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.PlaylistDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.TagDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.Config.PageResult;

import java.nio.file.Path;

import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Musics.MUSICS;
import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.MusicTags.MUSIC_TAGS;
import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.PlaylistsMusics.PLAYLISTS_MUSICS;

@Component
public class PlaylistMapper extends EntityAggregateMapper {
    private UserMapper userMapper;
    private MusicMapper musicMapper;
    private AuthorMapper authorMapper;
    private CategoryMapper categoryMapper;

    private WebProperties webProperties;

    protected List<String> entityMusicsAggregateAllowedMethods = List.of("GET", "POST");
    protected List<String> entityMusicsEntityAllowedMethods = List.of("DELETE");

    public PlaylistMapper(UserMapper userMapper) {
        super(URI.create("playlists/"));

        this.userMapper = userMapper;
    }

    public LinksDTO linkDTOEntityMusicItemsAggregate(long id) {
        return linkMapper.withCanonical(BASE_URI.resolve(id + "/musics"), entityMusicsAggregateAllowedMethods);
    }
    
    public LinksDTO linkDTOEntityMusicItemsEntity(long id, long musicID) {
        return linkMapper.with(List.of(
            new LinkMapper.Entry(LinksDTO.CANONICAL_LINK_NAME, musicMapper.getEntityLink(musicID), musicMapper.getEntityAllowedMethods()),
            new LinkMapper.Entry(LinksDTO.REF_LINK_NAME, BASE_URI.resolve(id + "/musics/" + musicID), entityMusicsEntityAllowedMethods)
        ));
    }

    public PageResult<List<MusicDTOPlaylistItem>, MusicDTOPlaylistItem> createPageDTOFromPageRecordsPlaylistItem(
        PageResult<Result<? extends Record>, Record> records, long playlistID
    ) {
        var resultList = new MusicDTOPlaylistItem[records.data().size()];
        int i = 0;
        Map<Long, MusicDTOPlaylistItem> musicMap = new HashMap<>(records.data().size());

        for (var record : records.data()) {
            long musicID = record.get(MUSICS.ID);
            MusicDTOPlaylistItem musicDTO = musicMap.get(musicID);

            if (musicDTO == null) {
                musicDTO = 
                    new MusicDTOPlaylistItem(
                        musicID,
                        new ArrayList<>(),
                        record.get(MUSICS.NAME),
                        webProperties.publicHtmlPath(Path.of(record.get(MUSICS.FILEPATH))),
                        authorMapper.createDTOFromRecord(record),
                        categoryMapper.createDTOFromRecord(record),
                        linkDTOEntityMusicItemsEntity(playlistID, musicID),
                        record.get(PLAYLISTS_MUSICS.ADDED_DATE).toInstant()
                    );

                musicMap.put(musicID, musicDTO);

                resultList[i++] = musicDTO;
            }

            var tagName = record.get(MUSIC_TAGS.NAME);
            if (tagName != null)
                musicDTO.getTags().add(new TagDTO(tagName));
        }

        return new PageResult<List<MusicDTOPlaylistItem>, MusicDTOPlaylistItem>(
            Arrays.asList(resultList),
            records.totalItems(),
            records.pageNumber(),
            records.pageSize(),
            records.totalPages()
        );
    }


    public PlaylistDTO createDTO(Playlist playlist) {
        return new PlaylistDTO(
            playlist.getId(),
            playlist.getName(),
            userMapper.createDTO(playlist.getCreatedBy()),
            playlist.getUriImage(),
            playlist.getCreateDate(),
            playlist.getUpdateDate(),
            linkDTOEntity(playlist.getId())
        );
    } 
}
