package com.gmail.mateusfcosta2002.musicwebsite.Entities.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URI;

@Converter(autoApply = true)
class URIConverter implements AttributeConverter<URI, String> {
    @Override
    public String convertToDatabaseColumn(URI attribute) {
        return attribute.toString();
    }

    @Override
    public URI convertToEntityAttribute(String dbData) {
        return URI.create(dbData);
    }
}
