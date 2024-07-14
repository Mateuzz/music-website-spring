package com.gmail.mateusfcosta2002.musicwebsite.Security.Entities;

import org.springframework.security.core.GrantedAuthority;

public class AppGranthedAuthority implements GrantedAuthority {
    private String authority;

    public AppGranthedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
