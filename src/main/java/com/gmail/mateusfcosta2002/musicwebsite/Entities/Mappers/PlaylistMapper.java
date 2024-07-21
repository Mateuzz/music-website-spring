package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import java.net.URI;

import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Playlist;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.PlaylistDTO;

@Component
public class PlaylistMapper extends EntityAggregateMapper {
    private UserMapper userMapper;

    public PlaylistMapper(UserMapper userMapper) {
        super(URI.create("playlists"));

        this.userMapper = userMapper;
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
