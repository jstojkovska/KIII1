package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.Availability;

import java.time.LocalDateTime;

public record CreateAvailabilityDto(LocalDateTime start_time, LocalDateTime end_time, int price, Long accommodation) {
    public Availability toAvailability(Accommodation accommodation) {
        return new Availability(start_time, end_time, price, accommodation);
    }
}
