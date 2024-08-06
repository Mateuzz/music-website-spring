package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinkDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;

@Component
public class LinkMapper {
    private URI appUri;

    public record Entry(String name, URI uri, Collection<String> actions) {}

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

    public LinksDTO with(List<Entry> links) {
        Map<String, LinkDTO> linksMap = new TreeMap<>();

        links.forEach(link -> {
            linksMap.put(
                link.name(), 
                new LinkDTO(appUri.resolve(link.uri()), link.actions())
            );
        });

        return new LinksDTO(linksMap);
    }

    public LinksDTO withCanonical(String relativeHref, Collection<String> actions) {
        return LinksDTO.withCanonical(
            new LinkDTO(
                appUri.resolve(relativeHref),
                actions
            )
        );
    }
    
    public LinksDTO withCanonical(URI relativeHref, Collection<String> actions) {
        return LinksDTO.withCanonical(
            new LinkDTO(
                appUri.resolve(relativeHref),
                actions
            )
        );
    }

}
