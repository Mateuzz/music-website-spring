package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musics")
public class Music {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

    @Column(length = 150, nullable = false) 
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Author author;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false) 
    private Category category;

    private int viewsCount;

    @CreationTimestamp
    Instant createDate;

    @UpdateTimestamp
    Instant updateDate;

    private String filepath;

    public Music() {}

    public Music(String name, Category category, String filepath, Author author, Set<Tag> tags) {
        this.name = name;
        this.category = category;
        this.filepath = filepath;
        this.tags = tags;
        this.author = author;
        this.viewsCount = 0;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Music)obj).id;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

}
