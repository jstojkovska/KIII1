package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Country;

public record CountryDetailsDto(String name, String continent) {
    public static CountryDetailsDto from(Country country){
        return new CountryDetailsDto(country.getName(), country.getContinent());
    }
}
