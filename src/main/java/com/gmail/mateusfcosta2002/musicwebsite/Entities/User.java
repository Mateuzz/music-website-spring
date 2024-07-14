package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(length = 100, nullable = false)
    @NaturalId
    private String username;

    @Column(length = 500, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    List<UserAuthority> authorities = new ArrayList<>();

    // For now we only need remove, since no user starts with playlist
    @OneToMany(mappedBy = "createdBy", cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Playlist> playlists = new ArrayList<>();

    public User(String username, String password, List<UserAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;

        for (var authority : authorities) {
            authority.setUser(this);
        }
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<UserAuthority> getAuthorities() {
        return authorities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
