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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.mateusfcosta2002.musicwebsite.Controllers.Responses.CommonResponses.MessageResponse;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.UserRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Entities.AppUserDetails;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Services.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

record RegisterForm(
        @Size(max = 100) @NotNull String username, 
        @Size(min = 12) @NotNull String password) {}

record LoginForm(@NotNull String username, @NotNull String password) {}


@RestController
public class LoginController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private UserRepository userRepository;
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    public LoginController(AuthenticationManager authenticationManager, UserService userService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid RegisterForm form) {
        var user = userService.getCommonUser(form.username(), form.password());
        var userDetails = AppUserDetailsService.createUserDetails(user);
        var token = UsernamePasswordAuthenticationToken.authenticated(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new MessageResponse("Username already exists"));
        } catch (Exception e) {
            throw e;
        }

        saveAuthentication(token);
        var userURL = userService.getUserUri(userDetails);

        return ResponseEntity
            .created(userURL)
            .body(new MessageResponse("Your account was created"));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid LoginForm form) {
        var token = UsernamePasswordAuthenticationToken.unauthenticated(form.username (), form.password ());
        var authentication = authenticationManager.authenticate(token);
        var userDetails = (AppUserDetails) saveAuthentication(authentication);

        return ResponseEntity
            .created(userService.getUserUri(userDetails))
            .body(new MessageResponse("Sucessfully login"));
    }

    @PostMapping(path = "/logout")
    public void logout(HttpServletRequest req, HttpServletResponse res, Authentication authentication) {
        var logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(req, res, authentication);
    }

    public Object saveAuthentication(Authentication authentication) {
        var context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);

        return authentication.getPrincipal();
    }

    public void deleteAuthentication() {
    }
}

