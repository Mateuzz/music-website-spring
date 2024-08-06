package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonValue;

public class LinksDTO implements Serializable {
    public static final String CANONICAL_LINK_NAME = "canonical";
    public static final String ROOT_LINK_NAME = "root";
    public static final String REF_LINK_NAME = "ref";

    @JsonValue
    private Map<String, LinkDTO> _links = new TreeMap<>();

    public LinksDTO() {
    }

    public static LinksDTO withCanonical(LinkDTO link) {
        return new LinksDTO(new TreeMap<>(){{
            put(CANONICAL_LINK_NAME, link);
        }});
    }

    public static LinksDTO withCanonicalAndRoot(LinkDTO canonical, LinkDTO root) {
        return new LinksDTO(new TreeMap<>(){{
            put(CANONICAL_LINK_NAME, canonical);
            put(ROOT_LINK_NAME, root);
        }});
    }

    public LinksDTO(Map<String, LinkDTO> _links) {
        this._links = _links;
    }

    public Map<String, LinkDTO> get_links() {
        return _links;
    }

    public void set_links(Map<String, LinkDTO> _links) {
        this._links = _links;
    }
}
