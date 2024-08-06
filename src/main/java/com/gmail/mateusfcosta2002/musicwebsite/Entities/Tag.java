package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "music_tags",
    indexes = {
        @Index(columnList = "name")
    }
)
public class Tag extends AbstractEntity<Long> {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @Column(length = 100, nullable = false)
    private String name;

    public Tag(String tagName) {
        this.name = tagName;
    }

    public Tag() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Tag)obj).name);
    }
 }
