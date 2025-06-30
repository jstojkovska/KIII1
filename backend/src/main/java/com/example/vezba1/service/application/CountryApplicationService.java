package com.example.vezba1.service.application;

import com.example.vezba1.dto.CreateCountryDto;
import com.example.vezba1.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto);

    Optional<DisplayCountryDto> save(CreateCountryDto countryDto);

    void deleteById(Long id);
}
