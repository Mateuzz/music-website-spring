package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import java.time.Instant;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.HashSet;

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
@Table(name= "playlists")
public class Playlist {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Music> musics = new HashSet<>();

    @Column(length = 150, nullable = false)
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    private String uriImage;

    @CreationTimestamp
    private Instant createDate;

    @UpdateTimestamp
    private Instant updateDate;

    public Playlist() {}

    public Playlist(String name, User createdBy) {
        this.name = name;
        this.createdBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Music> getMusics() {
        return musics;
    }

    public void setMusics(Set<Music> musics) {
        this.musics = musics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User ownerUser) {
        this.createdBy = ownerUser;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
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
