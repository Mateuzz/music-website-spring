package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;

public class AuthorDTO implements Serializable {
    private long id;
    private String name;
    private LinksDTO links;

    public AuthorDTO(LinksDTO links, long id, String name) {
        this.links = links;
        this.id = id;
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
    public LinksDTO getLinks() {
        return links;
    }
    public void setLinks(LinksDTO links) {
        this.links = links;
    }
}
