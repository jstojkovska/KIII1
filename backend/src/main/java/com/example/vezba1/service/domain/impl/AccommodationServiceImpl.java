package com.example.vezba1.service.domain.impl;

import com.example.vezba1.dto.AccommHostStatsDto;
import com.example.vezba1.dto.CategoryStatsDto;
import com.example.vezba1.dto.HostStatsDto;
import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.HostRental;
import com.example.vezba1.model.domain.Rental;
import com.example.vezba1.model.views.AccommodationsPerHostView;
import com.example.vezba1.repository.AccommodationRepository;
import com.example.vezba1.repository.AccommodationsPerHostViewRepository;
import com.example.vezba1.repository.HostRentalRepository;
import com.example.vezba1.repository.RentalRepository;
import com.example.vezba1.service.domain.AccommodationService;
import com.example.vezba1.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final RentalRepository rentalRepository;
    private final HostRentalRepository hostRentalRepository;
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, RentalRepository rentalRepository, HostRentalRepository hostRentalRepository, AccommodationsPerHostViewRepository accommodationsPerHostViewRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.rentalRepository = rentalRepository;
        this.hostRentalRepository = hostRentalRepository;
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id).map(x -> {
            if (accommodation.getName() != null) {
                x.setName(accommodation.getName());
            }
            if (accommodation.getNumRooms() != null) {
                x.setNumRooms(accommodation.getNumRooms());
            }
            if (accommodation.getCategoryType() != null) {
                x.setCategoryType(accommodation.getCategoryType());
            }
            if (accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
                x.setHost(hostService.findById(accommodation.getHost().getId()).get());
            }
            return accommodationRepository.save(x);
        });
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getName() != null && accommodation.getNumRooms() != null && accommodation.getCategoryType() != null &&
                accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
            return Optional.of(accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategoryType(), hostService.findById(accommodation.getHost().getId()).get(), accommodation.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
//        return accommodationRepository.findById(id).map(x -> {
//            x.setRented(true);
//            x.setBrojacAcc(x.getBrojacAcc()+1);
//            return accommodationRepository.save(x);
//        });

        return accommodationRepository.findById(id).map(accommodation -> {
            accommodation.setRented(true);

            Rental rental = new Rental();
            rental.setAccommodation(accommodation);
            rental.setHost(accommodation.getHost());
            rental.setRentalDate(LocalDateTime.now());
            rentalRepository.save(rental);

            HostRental hostRental = new HostRental();
            hostRental.setHost(accommodation.getHost());
            hostRental.setRentalDate(LocalDateTime.now());
            hostRentalRepository.save(hostRental);

            return accommodationRepository.save(accommodation);
        });
    }

//    @Override
//    public Optional<Accommodation> theMostPopular() {
//        List<Accommodation> allAccommodations = accommodationRepository.findAll();
//
//        if (allAccommodations.isEmpty()) {
//            return Optional.empty();
//        }
//
//        Accommodation mostPopular = allAccommodations.get(0);
//        for (Accommodation acc : allAccommodations) {
//            if (acc.getBrojacAcc() > mostPopular.getBrojacAcc()) {
//                mostPopular = acc;
//            }
//        }
//
//        return Optional.of(mostPopular);
//    }

    @Override
    public Optional<Accommodation> findMostPopularAccommodation() {
        return accommodationRepository.findAll().stream()
                .max(Comparator.comparingLong(x -> rentalRepository.countByAccommodationId(x.getId())));
    }

    @Override
    public List<CategoryStatsDto> getAvailableCountByCategoryUsingJavaLogic() {
        return accommodationRepository.findAll().stream()
                .filter(x -> !x.isRented())
                .collect(Collectors.groupingBy(Accommodation::getCategoryType, Collectors.counting()))
                .entrySet()
                .stream().map(x -> new CategoryStatsDto(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryStatsDto> getAvailableCountByCategory() {
        return accommodationRepository.getAvailableCountByCategory();
    }

    @Override
    public void refreshMaterializedView() {
        accommodationsPerHostViewRepository.refreshMaterializedView();
    }

    @Override
    public List<AccommodationsPerHostView> getAccommodationCountPerHost() {
        return accommodationsPerHostViewRepository.findAll();
    }

    @Override
    public Optional<Accommodation> getDetailsById(Long id) {
        return Optional.of(accommodationRepository.findById(id).orElseThrow());
    }

    @Override
    public Optional<Accommodation> toggleRented(Long id) {
        return accommodationRepository.findById(id)
                .map(acc -> {
                    acc.setRented(!acc.isRented());
                    return accommodationRepository.save(acc);
                });
    }

//    @Override
//    public List<AccommHostStatsDto> getAccommodationCountPerHost() {
//        return accommodationsPerHostViewRepository.findAll()
//                .stream()
//                .map(x -> new AccommHostStatsDto(x.getHostId(), x.getNumAccommodations().longValue()))
//                .collect(Collectors.toList());
//    }


}
