package com.customer.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.util.Optional;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {
    @Override
    public String convertToDatabaseColumn(LocalDate date) {
        return Optional.ofNullable(date) //
                .map(String::valueOf)
                .orElse(null);
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbStringDate) {
        return Optional.ofNullable(dbStringDate) //
                .map(LocalDate::parse)
                .orElse(null);
    }
}
