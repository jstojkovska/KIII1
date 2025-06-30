package com.example.vezba1.service.application.impl;

import com.example.vezba1.dto.CreateHostDto;
import com.example.vezba1.dto.DisplayHostDto;
import com.example.vezba1.dto.HostCountryStatsDto;
import com.example.vezba1.model.domain.Country;
import com.example.vezba1.model.projections.HostProjection;
import com.example.vezba1.model.views.HostsPerCountryView;
import com.example.vezba1.service.application.HostApplicationService;
import com.example.vezba1.service.domain.CountryService;
import com.example.vezba1.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll()
                .stream()
                .map(DisplayHostDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {
        Optional<Country> country = countryService.findById(createHostDto.country());
        return hostService.update(id, createHostDto.toHost(country.get()))
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto createHostDto) {
        Optional<Country> country = countryService.findById(createHostDto.country());
        return hostService.save(createHostDto.toHost(country.get()))
                .map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public Optional<DisplayHostDto> findMostPopularHost() {
        return hostService.findMostPopularHost()
                .map(DisplayHostDto::from);
    }

    @Override
    public List<HostCountryStatsDto> getAllHostPerCountry() {
        return hostService.getAllHostPerCountry()
                .stream()
                .map(HostCountryStatsDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<HostProjection> getAllHostNames() {
        return hostService.getAllHostNames();
    }
}
