package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Availability;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayAvailabilityDto(Long id, LocalDateTime start_time, LocalDateTime end_time, int price, Long availability) {
    public static DisplayAvailabilityDto from(Availability availability1) {
        return new DisplayAvailabilityDto(availability1.getId(), availability1.getStart_time(), availability1.getEnd_time(), availability1.getPrice(), availability1.getAccommodation().getId());
    }

    public List<DisplayAvailabilityDto> from(List<Availability> availabilities) {
        return availabilities
                .stream()
                .map(DisplayAvailabilityDto::from)
                .collect(Collectors.toList());
    }
}
