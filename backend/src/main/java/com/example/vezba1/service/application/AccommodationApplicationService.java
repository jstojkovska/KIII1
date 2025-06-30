package com.example.vezba1.service.application;

import com.example.vezba1.dto.*;
import com.example.vezba1.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto);

    void deleteById(Long id);
    Optional<DisplayAccommodationDto> markAsRented(Long id);
//    Optional<DisplayAccommodationDto> theMostPopular();
    Optional<DisplayAccommodationDto> findMostPopularAccommodation();
    List<CategoryStatsDto> getAvailableCountByCategoryUsingJavaLogic();
    List<CategoryStatsDto> getAvailableCountByCategory();
    List<AccommHostStatsDto> getAccommodationCountPerHost();
    Optional<Details> getDetailsById(Long id);
    Optional<DisplayAccommodationDto> toggleRented(Long id);
}
