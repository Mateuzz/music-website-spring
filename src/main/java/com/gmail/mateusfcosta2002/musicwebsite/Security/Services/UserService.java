package com.gmail.mateusfcosta2002.musicwebsite.Security.Services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.User;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.UserAuthority;

@Component
public class UserService {
    public static final UserAuthority USER_ROLE = UserAuthority.role("USER");
    public static final UserAuthority ADMIN_ROLE = UserAuthority.role("ADMIN");

    private PasswordEncoder encoder;

    public UserService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User createCommonUser(String username, String password) {
        return new User(username, encoder.encode(password) , List.of(USER_ROLE));
    }
}
