package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.io.Serializable;
import java.util.Collection;

public class LinkDTO implements Serializable {
    private String href;
    private Collection<String> actions;

    public LinkDTO(String href, Collection<String> actions) {
        this.href = href;
        this.actions = actions;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Collection<String> getActions() {
        return actions;
    }

    public void setActions(Collection<String> actions) {
        this.actions = actions;
    }

}
