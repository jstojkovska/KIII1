package com.example.vezba1.service.application;

import com.example.vezba1.dto.CreateReservationDto;
import com.example.vezba1.dto.DisplayReservationDto;
import com.example.vezba1.model.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationApplicationService {
    Optional<DisplayReservationDto> createReservation(CreateReservationDto reservation);
    Optional<DisplayReservationDto> findByIdReservation(Long id);
    List<DisplayReservationDto> getAllReservations();
}
