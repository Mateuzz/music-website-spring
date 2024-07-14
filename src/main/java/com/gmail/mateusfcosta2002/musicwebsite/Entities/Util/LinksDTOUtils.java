package com.gmail.mateusfcosta2002.musicwebsite.Entities.Util;

import java.net.URI;
import java.util.Collection;
import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinkDTO;

@Component
public class LinksDTOUtils {
    private URI appUri;

    public LinksDTOUtils(WebProperties properties) {
        this.appUri = properties.getAppUri();
    }

    public LinkDTO linkDTO(String relativeHref, Collection<String> actions) {
        return new LinkDTO(
            appUri.resolve(relativeHref).toString(),
            actions
        );
    }
}
