package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.enumerations.CategoryType;
import com.example.vezba1.model.domain.Host;

public record CreateAccommodationDto(String name, CategoryType categoryType, Long host, Integer numRooms){
    public Accommodation toAccommodation(Host host){
        return new Accommodation(name,categoryType,host,numRooms);
    }
}
