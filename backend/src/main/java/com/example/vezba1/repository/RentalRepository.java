package com.example.vezba1.repository;

import com.example.vezba1.model.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    long countByAccommodationId(Long accommodationId);
}
