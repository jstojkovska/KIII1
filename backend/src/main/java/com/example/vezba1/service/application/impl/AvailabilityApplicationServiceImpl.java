package com.example.vezba1.service.application.impl;

import com.example.vezba1.dto.CreateAvailabilityDto;
import com.example.vezba1.dto.DisplayAvailabilityDto;
import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.service.application.AvailabilityApplicationService;
import com.example.vezba1.service.domain.AccommodationService;
import com.example.vezba1.service.domain.AvailabilityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailabilityApplicationServiceImpl implements AvailabilityApplicationService {
    private final AvailabilityService availabilityService;
    private final AccommodationService accommodationService;

    public AvailabilityApplicationServiceImpl(AvailabilityService availabilityService, AccommodationService accommodationService) {
        this.availabilityService = availabilityService;
        this.accommodationService = accommodationService;
    }

    @Override
    public Optional<DisplayAvailabilityDto> createAvailability(CreateAvailabilityDto availability) {
        Optional<Accommodation> acc = accommodationService.findById(availability.accommodation());
        return availabilityService.createAvailability(availability.toAvailability(acc.get())).map(DisplayAvailabilityDto::from);
    }

    @Override
    public Optional<DisplayAvailabilityDto> getAvailabilityById(Long id) {
        return availabilityService.getAvailabilityById(id).map(DisplayAvailabilityDto::from);
    }

    @Override
    public List<DisplayAvailabilityDto> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities().stream()
                .map(DisplayAvailabilityDto::from)
                .collect(Collectors.toList());
    }
}
