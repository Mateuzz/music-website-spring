package com.gmail.mateusfcosta2002.musicwebsite.Security.Entities;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails {
    private Collection<? extends GrantedAuthority> authorities;
    private String username;
    private String password;
    private long id;

    public AppUserDetails(long id, String password, Collection<? extends GrantedAuthority> authorities, String username) {
        this.password = password;
        this.authorities = authorities;
        this.username = username;
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() { 
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }
}
