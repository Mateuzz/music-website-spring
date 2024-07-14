package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musics")
public class Music {
    @Id @GeneratedValue 
    private long id;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

    @Column(length = 150) 
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    private Category category;

    private int durationInSeconds;

    private String filepath;

    public Music() {}

    public Music(String name, Category category, int durationInSeconds, String filepath, Author author, Set<Tag> tags) {
        this.name = name;
        this.category = category;
        this.durationInSeconds = durationInSeconds;
        this.filepath = filepath;
        this.tags = tags;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
