package com.example.vezba1.service.domain.impl;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.Availability;
import com.example.vezba1.repository.AccommodationRepository;
import com.example.vezba1.repository.AvailabilityRepository;
import com.example.vezba1.service.domain.AvailabilityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final AccommodationRepository accommodationRepository;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, AccommodationRepository accommodationRepository) {
        this.availabilityRepository = availabilityRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public Optional<Availability> createAvailability(Availability availability) {
        Accommodation accommodation = accommodationRepository.findById(availability.getAccommodation().getId()).orElseThrow(() ->
                new IllegalArgumentException("Accommodation not found with the provided ID"));

        Availability availability1 = new Availability(
                availability.getStart_time(),
                availability.getEnd_time(),
                availability.getPrice(),
                accommodation
        );
        return Optional.of(availabilityRepository.save(availability1));

    }

    @Override
    public Optional<Availability> getAvailabilityById(Long id) {
        return Optional.of(availabilityRepository.findById(id).get());
    }

    @Override
    public List<Availability> getAllAvailabilities() {
        return new ArrayList<>(availabilityRepository.findAll());
    }
}
