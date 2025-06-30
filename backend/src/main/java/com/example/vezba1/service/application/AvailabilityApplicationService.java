package com.example.vezba1.service.application;

import com.example.vezba1.dto.CreateAvailabilityDto;
import com.example.vezba1.dto.DisplayAvailabilityDto;
import com.example.vezba1.model.domain.Availability;

import java.util.List;
import java.util.Optional;

public interface AvailabilityApplicationService {
    Optional<DisplayAvailabilityDto> createAvailability(CreateAvailabilityDto availability);
    Optional<DisplayAvailabilityDto> getAvailabilityById(Long id);
    List<DisplayAvailabilityDto> getAllAvailabilities();
}
