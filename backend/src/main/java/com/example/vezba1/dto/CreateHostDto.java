package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Country;
import com.example.vezba1.model.domain.Host;

public record CreateHostDto(String name, String surname, Long country) {
    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
}
