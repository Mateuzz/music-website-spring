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
    private LinksDTO _links;

    public MusicDTO(long id, Collection<TagDTO> tags, String name, AuthorDTO author, CategoryDTO category, String htmlFilepath, LinksDTO _links) {
        this.id = id;
        this.filepath = htmlFilepath;
        this.tags = tags;
        this.name = name;
        this.author = author;
        this.category = category;
        this._links = _links;
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

    public LinksDTO get_links() {
        return _links;
    }

    public void set_links(LinksDTO _links) {
        this._links = _links;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String uri) {
        this.filepath = uri;
    }
   
}
