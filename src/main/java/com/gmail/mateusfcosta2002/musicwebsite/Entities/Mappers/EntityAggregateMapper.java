package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;

public class EntityAggregateMapper {
    @Autowired
    protected LinkMapper linkMapper;

    protected List<String> entityAllowedMethods = List.of("GET", "DELETE");
    protected List<String> aggregateAllowedMethods = List.of("GET", "POST");

	public final URI BASE_URI;

    public EntityAggregateMapper(URI baseURI) {
        this.BASE_URI = baseURI;
    }

    public EntityAggregateMapper(List<String> entityAllowedMethods, List<String> aggregateAllowedMethods, URI baseURI) {
        this.entityAllowedMethods = entityAllowedMethods;
        this.aggregateAllowedMethods = aggregateAllowedMethods;
        this.BASE_URI = baseURI;
    }

    public List<String> getEntityAllowedMethods() {
		return entityAllowedMethods;
	}

	public List<String> getAggregateAllowedMethods() {
		return aggregateAllowedMethods;
	}

    public URI getEntityLink(long id) {
        return BASE_URI.resolve(String.valueOf(id));
    }

    public LinksDTO linkDTOEntity(long id) {
        System.out.println(BASE_URI);
        System.out.println(BASE_URI.resolve(String.valueOf(id)));
        return linkMapper.withCanonical(BASE_URI.resolve(String.valueOf(id)), entityAllowedMethods);
    }

    public LinksDTO linkDTOAggregate() {
        return linkMapper.withCanonical(BASE_URI, aggregateAllowedMethods);
    }
}

