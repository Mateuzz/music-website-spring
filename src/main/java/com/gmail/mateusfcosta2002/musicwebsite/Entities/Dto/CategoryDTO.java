package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private long id;
    private int nesting;
    private Long parentID;
    private String name;
    private LinksDTO links;

    public CategoryDTO(Long parent_id, long id, int nesting, String name, LinksDTO links) {
        this.parentID = parent_id;
        this.id = id;
        this.nesting = nesting;
        this.name = name;
        this.links = links;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getNesting() {
        return nesting;
    }
    public void setNesting(int nesting) {
        this.nesting = nesting;
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

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parent_id) {
        this.parentID = parent_id;
    }

}
