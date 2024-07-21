package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;
import java.util.Collection;

public class MusicDTO implements Serializable {
    private long id;
    private Collection<TagDTO> tags;
    private String name;
    private String filepath;
    private AuthorDTO author;
    private CategoryDTO category;
    private LinksDTO links;

    public MusicDTO(long id, Collection<TagDTO> tags, String name, AuthorDTO author, CategoryDTO category, String htmlFilepath, LinksDTO links) {
        this.id = id;
        this.filepath = htmlFilepath;
        this.tags = tags;
        this.name = name;
        this.author = author;
        this.category = category;
        this.links = links;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Collection<TagDTO> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public LinksDTO getLinks() {
        return links;
    }

    public void setLinks(LinksDTO links) {
        this.links = links;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String uri) {
        this.filepath = uri;
    }
   
}
