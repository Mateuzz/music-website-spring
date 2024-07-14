package com.gmail.mateusfcosta2002.musicwebsite.Security.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.User;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.UserRepository;

import com.gmail.mateusfcosta2002.musicwebsite.Security.Entities.*;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Entities.Dto.AppUserDetailsDTO;

@Component
public class AppUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  userRepository
            .findWithAuthoritiesByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " does not exists"));

        var details = createUserDetails(user);
        return details;
    }

    public static AppUserDetails createUserDetails(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (var authority : user.getAuthorities()) {
            authorities.add(new AppGranthedAuthority(authority.getId().getAuthority()));
        }

        return new AppUserDetails(user.getId(), user.getPassword(), authorities, user.getUsername());
    }

    public static AppUserDetailsDTO createDetailsDTO(AppUserDetails userDetails) {
        return new AppUserDetailsDTO(userDetails.getId(), userDetails.getUsername());
    }
}
