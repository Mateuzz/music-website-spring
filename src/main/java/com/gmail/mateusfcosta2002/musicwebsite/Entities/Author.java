package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
    @Id @GeneratedValue
    private long id;

    @Column(length = 150, nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Music> musics = new HashSet<>();

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Music> getMusics() {
        return musics;
    }

    public void setMusics(Set<Music> musics) {
        this.musics = musics;
    }
}
