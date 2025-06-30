package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Host;

public record HostDetailsDto(String name, String surname, String country,String continent) {
    public static HostDetailsDto from(Host host){
        return new HostDetailsDto(host.getName(), host.getSurname(), host.getCountry().getName(),host.getCountry().getContinent());
    }
}
