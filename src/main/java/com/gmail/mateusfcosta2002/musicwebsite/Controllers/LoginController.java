package com.gmail.mateusfcosta2002.musicwebsite.Controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra.CommonResponses.MessageResponse;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers.UserMapper;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.UserRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Entities.AppUserDetails;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Entities.Dto.AppUserDetailsDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Services.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;

record LoginForm(@NotNull String username, @NotNull String password) {
}

record RegisterForm(
    @Size(max = 100, min = 3) @NotNull String username,
    @Size(min = 12) @NotNull String password) {
}

@RestController
public class LoginController {
    private final SecurityContextHolderStrategy SECURITY_CONTEXT_HOLDER_STRATEGY = SecurityContextHolder.getContextHolderStrategy();

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private UserRepository userRepository;
    private UserMapper userMapper;

    public LoginController(AuthenticationManager authenticationManager, UserService userService,
            UserRepository userRepository,
            UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid RegisterForm form) {
        var user = userService.createCommonUser(form.username(), form.password());
        var userDetails = AppUserDetailsService.createUserDetails(user);
        var token = UsernamePasswordAuthenticationToken.authenticated(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Username already exists"));
        }

        saveAuthentication(token);
        var userURL = userMapper.getEntityLink(userDetails);

        return ResponseEntity
                .created(userURL)
                .body(new MessageResponse("Your account was created"));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid LoginForm form) {
        var token = UsernamePasswordAuthenticationToken.unauthenticated(form.username(), form.password());
        var authentication = authenticationManager.authenticate(token);
        var userDetails = (AppUserDetails) saveAuthentication(authentication);

        return ResponseEntity
                .created(userMapper.getEntityLink(userDetails))
                .body(new MessageResponse("Sucessfully login"));
    }

    @PostMapping(path = "/logout")
    public void logout(HttpServletRequest req, HttpServletResponse res, Authentication authentication) {
        logoutHandler(req, res, authentication);
    }

    @DeleteMapping(path = "/register")
    @Transactional
    public AppUserDetailsDTO deleteUser(HttpServletRequest req, HttpServletResponse res, Authentication authentication) {
        var userDetails = (AppUserDetails) authentication.getPrincipal();
        userRepository.deleteById(userDetails.getId());
        logoutHandler(req, res, authentication);

        return AppUserDetailsService.createDetailsDTO(userDetails);
    }

    private Object saveAuthentication(Authentication authentication) {
        var context = SECURITY_CONTEXT_HOLDER_STRATEGY.createEmptyContext();
        context.setAuthentication(authentication);
        SECURITY_CONTEXT_HOLDER_STRATEGY.setContext(context);

        return authentication.getPrincipal();
    }

    private void logoutHandler(HttpServletRequest req, HttpServletResponse res, Authentication authentication) {
        var logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(req, res, authentication);
    }
}
