package com.example.vezba1.service.domain;

import com.example.vezba1.model.domain.Availability;

import java.util.List;
import java.util.Optional;

public interface AvailabilityService {
    Optional<Availability> createAvailability(Availability availability);
    Optional<Availability> getAvailabilityById(Long id);
    List<Availability> getAllAvailabilities();
}
