package com.example.vezba1.service.application.impl;

import com.example.vezba1.dto.*;
import com.example.vezba1.model.domain.Host;
import com.example.vezba1.service.application.AccommodationApplicationService;
import com.example.vezba1.service.domain.AccommodationService;
import com.example.vezba1.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll()
                .stream()
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = hostService.findById(createAccommodationDto.host());
        return accommodationService.update(id, createAccommodationDto.toAccommodation(host.get()))
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = hostService.findById(createAccommodationDto.host());
        return accommodationService.save(createAccommodationDto.toAccommodation(host.get()))
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService.markAsRented(id)
                .map(DisplayAccommodationDto::from);
    }

//    @Override
//    public Optional<DisplayAccommodationDto> theMostPopular() {
//        return accommodationService.theMostPopular()
//                .map(DisplayAccommodationDto::from);
//    }

    @Override
    public Optional<DisplayAccommodationDto> findMostPopularAccommodation() {
        return accommodationService.findMostPopularAccommodation()
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public List<CategoryStatsDto> getAvailableCountByCategoryUsingJavaLogic() {
        return accommodationService.getAvailableCountByCategoryUsingJavaLogic();
    }

    @Override
    public List<CategoryStatsDto> getAvailableCountByCategory() {
        return accommodationService.getAvailableCountByCategory();
    }

    @Override
    public List<AccommHostStatsDto> getAccommodationCountPerHost() {
        return accommodationService.getAccommodationCountPerHost()
                .stream()
                .map(AccommHostStatsDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Details> getDetailsById(Long id) {
        return accommodationService.getDetailsById(id)
                .map(Details::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> toggleRented(Long id) {
        return accommodationService.toggleRented(id)
                .map(DisplayAccommodationDto::from);
    }

}
