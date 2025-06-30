package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.enumerations.CategoryType;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(Long id, String name, CategoryType categoryType, Long host, Integer numRooms,
                                      boolean isRented) {
    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(accommodation.getId(), accommodation.getName(), accommodation.getCategoryType(), accommodation.getHost().getId(), accommodation.getNumRooms(), accommodation.isRented());
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations
                .stream()
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }
}
