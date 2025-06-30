package com.example.vezba1.service.application;

import com.example.vezba1.dto.CreateHostDto;
import com.example.vezba1.dto.DisplayHostDto;
import com.example.vezba1.dto.HostCountryStatsDto;
import com.example.vezba1.model.projections.HostProjection;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto);

    Optional<DisplayHostDto> save(CreateHostDto createHostDto);

    void deleteById(Long id);
    Optional<DisplayHostDto> findMostPopularHost();
    List<HostCountryStatsDto> getAllHostPerCountry();
    List<HostProjection> getAllHostNames();

}
