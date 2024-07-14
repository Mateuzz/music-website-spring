package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;
import java.net.URI;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonValue;

public class LinksDTO implements Serializable {
    public static final String CANONICAL_LINK_NAME = "_canonical";
    public static final String ROOT_LINK_NAME = "_root";

    @JsonValue
    private Map<String, LinkDTO> links;

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

    public LinksDTO(Map<String, LinkDTO> links) {
        this.links = links;
    }

    public Map<String, LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(Map<String, LinkDTO> links) {
        this.links = links;
    }
}
