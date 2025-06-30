package com.example.vezba1.repository;

import com.example.vezba1.model.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByAccommodationIdAndEndDateAfterAndStartDateBefore(
            Long accommodationId,
            LocalDate startDate,
            LocalDate endDate
    );

}
