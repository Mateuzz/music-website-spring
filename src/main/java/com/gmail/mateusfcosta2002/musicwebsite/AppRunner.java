package com.gmail.mateusfcosta2002.musicwebsite;

import com.gmail.mateusfcosta2002.musicwebsite.Controllers.LoginController;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.*;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Entities.AppUserDetails;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Services.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource.AuthenticationType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AppRunner implements CommandLineRunner {
    private static final String AppUserDetails = null;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private LoginController loginController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder encoder;

    public void run(String... args) throws Exception {
        run1();
    } 

    @Transactional
    void run1() {
    }
}
