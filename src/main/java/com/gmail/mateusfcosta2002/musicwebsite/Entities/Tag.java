package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag extends AbstractEntity<String> {
    @Id
    @Column(length = 50)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Music> musics = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    @Override
    @Nullable
    public String getId() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Tag)obj).name);
    }
 }
