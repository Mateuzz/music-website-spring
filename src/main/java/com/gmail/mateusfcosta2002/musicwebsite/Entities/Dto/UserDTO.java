package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private long id;
    private String name;
    private LinksDTO links;

    public UserDTO(long id, String name, LinksDTO links) {
        this.id = id;
        this.name = name;
        this.links = links;
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
