package com.gmail.mateusfcosta2002.musicwebsite.Security.Services;

import java.net.URI;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.User;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.UserAuthority;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinkDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.UserDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Util.LinksDTOUtils;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.UserRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Entities.AppUserDetails;

@Component
public class UserService {
    public static final UserAuthority USER_ROLE = UserAuthority.role("USER");
    public static final UserAuthority ADMIN_ROLE = UserAuthority.role("ADMIN");

    private URI appURI;
    private PasswordEncoder encoder;
    private LinksDTOUtils linksDTOUtils;

    public UserService(WebProperties webProperties, PasswordEncoder encoder, LinksDTOUtils linksDTOUtils) {
        this.appURI = webProperties.getAppUri();
        this.encoder = encoder;
        this.linksDTOUtils = linksDTOUtils;
    }

    public LinkDTO getCanonicalLink(long id) {
        return linksDTOUtils.linkDTO("users/" + id, List.of("GET", "DELETE"));
    }

    public LinkDTO getRootLink() {
        return linksDTOUtils.linkDTO("users", List.of("GET", "POST"));
    }

    public UserDTO createDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), LinksDTO.withCanonical(getCanonicalLink(user.getId())));
    }

    public User getCommonUser(String username, String plainPassword) {
        return new User(username , encoder.encode(plainPassword) , List.of(USER_ROLE));
    }

    public URI getUserUri(AppUserDetails userDetails) {
        return appURI.resolve("users/" + userDetails.getId());
    }
}
