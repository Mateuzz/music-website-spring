package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import java.net.URI;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinkDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;

@Component
public class LinkMapper {
    private URI appUri;

    public LinkMapper(WebProperties properties) {
        this.appUri = properties.APP_URI;
    }

    public LinkDTO linkDTO(String relativeHref, Collection<String> actions) {
        return new LinkDTO(
            appUri.resolve(relativeHref).toString(),
            actions
        );
    }

    public LinkDTO linkDTO(URI relativeHref, Collection<String> actions) {
        return new LinkDTO(
            appUri.resolve(relativeHref).toString(),
            actions
        );
    }

    public LinksDTO withCanonical(String relativeHref, Collection<String> actions) {
        return LinksDTO.withCanonical(
            new LinkDTO(
                appUri.resolve(relativeHref).toString(),
                actions
            )
        );
    }

    public LinksDTO withCanonical(URI relativeHref, Collection<String> actions) {
        return LinksDTO.withCanonical(
            new LinkDTO(
                appUri.resolve(relativeHref).toString(),
                actions
            )
        );
    }

}
