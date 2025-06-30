package com.example.vezba1.service.domain;

import com.example.vezba1.dto.AccommHostStatsDto;
import com.example.vezba1.dto.CategoryStatsDto;
import com.example.vezba1.dto.Details;
import com.example.vezba1.dto.HostStatsDto;
import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.Host;
import com.example.vezba1.model.views.AccommodationsPerHostView;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> save(Accommodation accommodation);

    void deleteById(Long id);

    Optional<Accommodation> markAsRented(Long id);

//    Optional<Accommodation> theMostPopular();

    Optional<Accommodation> findMostPopularAccommodation();

    List<CategoryStatsDto> getAvailableCountByCategoryUsingJavaLogic();

    List<CategoryStatsDto> getAvailableCountByCategory();
    void refreshMaterializedView();
//    List<AccommHostStatsDto> getAccommodationCountPerHost();
    List<AccommodationsPerHostView> getAccommodationCountPerHost();
    Optional<Accommodation> getDetailsById(Long id);
    Optional<Accommodation> toggleRented(Long id);
}
