package com.example.vezba1.service.domain.impl;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.Reservation;
import com.example.vezba1.model.domain.User;
import com.example.vezba1.repository.AccommodationRepository;
import com.example.vezba1.repository.ReservationRepository;
import com.example.vezba1.repository.UserRepository;
import com.example.vezba1.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, AccommodationRepository accommodationRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
    }

//    @Override
//    public Optional<Reservation> createReservation(Reservation reservation) {
//        User user = userRepository.findById(reservation.getUser().getUsername()).orElseThrow();
//        Accommodation accommodation = accommodationRepository.findById(reservation.getAccommodation().getId()).orElseThrow();
//
//        Reservation reservation1 = new Reservation(
//                user,
//                accommodation,
//                reservation.getStartDate(),
//                reservation.getEndDate()
//        );
//        return Optional.of(reservationRepository.save(reservation1));
//    }

    @Override
    public Optional<Reservation> createReservation(Reservation reservation) {
        Long accId = reservation.getAccommodation().getId();
        LocalDate start = reservation.getStartDate();
        LocalDate end = reservation.getEndDate();

        List<Reservation> overlapping = reservationRepository.findAllByAccommodationIdAndEndDateAfterAndStartDateBefore(accId, start, end);
        if (!overlapping.isEmpty()) {
            return Optional.empty();
        }

        User user = userRepository.findById(reservation.getUser().getUsername()).orElseThrow();
        Accommodation accommodation = accommodationRepository.findById(accId).orElseThrow();

        Reservation newRes = new Reservation(user, accommodation, start, end);
        return Optional.of(reservationRepository.save(newRes));
    }



    @Override
    public Optional<Reservation> findByIdReservation(Long id) {
        return Optional.of(reservationRepository.findById(id).get());
    }

    @Override
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservationRepository.findAll());
    }


}
