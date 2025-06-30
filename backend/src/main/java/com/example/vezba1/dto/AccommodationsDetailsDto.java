package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Accommodation;

public record AccommodationsDetailsDto(Long id,String name, String category, Integer numRooms) {
    public static AccommodationsDetailsDto from(Accommodation accommodation) {
        return new AccommodationsDetailsDto(accommodation.getId(), accommodation.getName(),
                accommodation.getCategoryType().name(), accommodation.getNumRooms());

    }
}
