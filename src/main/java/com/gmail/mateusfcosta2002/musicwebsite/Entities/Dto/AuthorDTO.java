package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;

public class AuthorDTO implements Serializable {
    private long id;
    private String name;
    private LinksDTO _links;

    public AuthorDTO(LinksDTO _links, long id, String name) {
        this._links = _links;
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

	public LinksDTO get_links() {
		return _links;
	}

	public void set_links(LinksDTO _links) {
		this._links = _links;
	}
}
