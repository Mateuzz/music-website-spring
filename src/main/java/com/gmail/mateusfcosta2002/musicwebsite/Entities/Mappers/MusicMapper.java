package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Music;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.MusicDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.TagDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.Config.PageResult;
import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Musics.MUSICS;
import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.MusicTags.MUSIC_TAGS;
import java.nio.file.Path;

@Component
public class MusicMapper extends EntityAggregateMapper {
    private WebProperties webProperties;
    private AuthorMapper authorMapper;
    private CategoryMapper categoryMapper;

    public MusicMapper(AuthorMapper authorMapper, WebProperties webProperties, CategoryMapper categoryMapper) {
        super(URI.create("musics/"));
        this.categoryMapper = categoryMapper;
        this.authorMapper = authorMapper;
        this.webProperties = webProperties;
    }

    public MusicDTO createDTO(Music music) {
        var htmlFilepath = webProperties.publicHtmlPath(Path.of(music.getFilepath()));
        var tagsDTO = new TagDTO[music.getTags().size()];

        int i = 0;

        for (var tag : music.getTags()) {
            tagsDTO[i++] = new TagDTO(tag.getName());
        }

        return new MusicDTO(
                music.getId(),
                Arrays.asList(tagsDTO),
                music.getName(),
                authorMapper.createDTO(music.getAuthor()),
                categoryMapper.createDTO(music.getCategory()),
                htmlFilepath,
                linkDTOEntity(music.getId()));
    } 

    public PageResult<List<MusicDTO>, MusicDTO> createPageDTOFromPageRecords(
        PageResult<Result<? extends Record>, Record> records
    ) {
        var resultList = new MusicDTO[records.data().size()];
        int i = 0;
        Map<Long, MusicDTO> musicMap = new HashMap<>(records.data().size());

        for (var record : records.data()) {
            long id = record.get(MUSICS.ID);
            MusicDTO musicDTO = musicMap.get(id);

            if (musicDTO == null) {
                musicDTO = 
                    new MusicDTO(
                        id,
                        new ArrayList<>(),
                        record.get(MUSICS.NAME),
                        authorMapper.createDTOFromRecord(record),
                        categoryMapper.createDTOFromRecord(record),
                        webProperties.publicHtmlPath(Path.of(record.get(MUSICS.FILEPATH))),
                        linkDTOEntity(id)
                    );

                musicMap.put(id, musicDTO);

                resultList[i++] = musicDTO;
            }

            var tagName = record.get(MUSIC_TAGS.NAME);

            if (tagName != null)
                musicDTO.getTags().add(new TagDTO(tagName));
        }

        return new PageResult<List<MusicDTO>, MusicDTO>(
            Arrays.asList(resultList),
            records.totalItems(),
            records.pageNumber(),
            records.pageSize(),
            records.totalPages()
        );
    }
}
