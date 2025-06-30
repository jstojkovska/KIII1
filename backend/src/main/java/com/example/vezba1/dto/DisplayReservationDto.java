package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.Reservation;
import com.example.vezba1.model.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DisplayReservationDto(Long id, String user, Long accommodation, LocalDate startRes,LocalDate endDate) {
    public static DisplayReservationDto from(Reservation reservation) {
        return new DisplayReservationDto(reservation.getId(), reservation.getUser().getUsername(), reservation.getAccommodation().getId(), reservation.getStartDate(),reservation.getEndDate());
    }
}
