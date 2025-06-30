package com.example.vezba1.service.domain;

import com.example.vezba1.model.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> createReservation(Reservation reservation);
    Optional<Reservation> findByIdReservation(Long id);
    List<Reservation> getAllReservations();

}
