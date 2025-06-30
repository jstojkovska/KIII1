package com.example.vezba1.repository;

import com.example.vezba1.model.domain.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability,Long> {
}
