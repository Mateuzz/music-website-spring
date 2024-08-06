package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private long id;
    private String name;
    private LinksDTO _links;

    public UserDTO(long id, String name, LinksDTO _links) {
        this.id = id;
        this.name = name;
        this._links = _links;
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
