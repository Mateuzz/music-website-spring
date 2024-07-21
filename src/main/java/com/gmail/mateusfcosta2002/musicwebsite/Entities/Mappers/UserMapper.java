package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import java.net.URI;

import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.User;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.UserDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Entities.AppUserDetails;

@Component
public class UserMapper extends EntityAggregateMapper {
    public UserMapper() {
        super(URI.create("playlists"));
    }

    public UserDTO createDTO(User user) {
        return new UserDTO(
                user.getId(), 
                user.getUsername(), 
                linkDTOEntity(user.getId()));
    } 

    public URI getEntityLink(AppUserDetails userDetails) {
        return getEntityLink(userDetails.getId());
    }
}
