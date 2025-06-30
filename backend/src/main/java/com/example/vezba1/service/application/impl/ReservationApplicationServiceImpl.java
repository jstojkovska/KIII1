package com.example.vezba1.service.application.impl;

import com.example.vezba1.dto.CreateReservationDto;
import com.example.vezba1.dto.DisplayReservationDto;
import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.User;
import com.example.vezba1.service.application.ReservationApplicationService;
import com.example.vezba1.service.domain.AccommodationService;
import com.example.vezba1.service.domain.ReservationService;
import com.example.vezba1.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService {
    private final ReservationService reservationService;
    private final AccommodationService accommodationService;
    private final UserService userService;

    public ReservationApplicationServiceImpl(ReservationService reservationService, AccommodationService accommodationService, UserService userService) {
        this.reservationService = reservationService;
        this.accommodationService = accommodationService;
        this.userService = userService;
    }

    @Override
    public Optional<DisplayReservationDto> createReservation(CreateReservationDto reservation) {
        Optional<Accommodation> accommodation = accommodationService.findById(reservation.accommodation());
        Optional<User> user = Optional.ofNullable(userService.findByUsername(reservation.user()));

        return reservationService.createReservation(reservation.toReservation(user.get(), accommodation.get())).map(DisplayReservationDto::from);
    }

    @Override
    public Optional<DisplayReservationDto> findByIdReservation(Long id) {
        return reservationService.findByIdReservation(id).map(DisplayReservationDto::from);
    }

    @Override
    public List<DisplayReservationDto> getAllReservations() {
        return reservationService.getAllReservations()
                .stream()
                .map(DisplayReservationDto::from)
                .collect(Collectors.toList());
    }
}
