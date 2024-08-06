package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private long id;
    private int nesting;
    private Long parentID;
    private String name;
    private LinksDTO _links;

    public CategoryDTO(Long parent_id, long id, int nesting, String name, LinksDTO _links) {
        this.parentID = parent_id;
        this.id = id;
        this.nesting = nesting;
        this.name = name;
        this._links = _links;
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

	public LinksDTO get_links() {
		return _links;
	}

	public void set_links(LinksDTO _links) {
		this._links = _links;
	}

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parent_id) {
        this.parentID = parent_id;
    }

}
