package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.Reservation;
import com.example.vezba1.model.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateReservationDto(String user, Long accommodation, LocalDate startDate, LocalDate endDate) {
    public Reservation toReservation(User user, Accommodation accommodation){
        return new Reservation(user, accommodation, startDate, endDate);
    }
}

