package com.gmail.mateusfcosta2002.musicwebsite.Entities.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;

@Converter(autoApply = true)
class DurationConverter implements AttributeConverter<Duration, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Duration attribute) {
        return Integer.valueOf((int)attribute.toSeconds());
    }

    @Override
    public Duration convertToEntityAttribute(Integer dbData) {
        return Duration.ofSeconds(dbData);
    }
}
