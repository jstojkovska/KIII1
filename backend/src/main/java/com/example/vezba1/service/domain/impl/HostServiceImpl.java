package com.example.vezba1.service.domain.impl;

import com.example.vezba1.dto.HostCountryStatsDto;
import com.example.vezba1.events.HostCreatedEvent;
import com.example.vezba1.model.domain.Host;
import com.example.vezba1.model.projections.HostProjection;
import com.example.vezba1.model.views.HostsPerCountryView;
import com.example.vezba1.repository.CountryRepository;
import com.example.vezba1.repository.HostRentalRepository;
import com.example.vezba1.repository.HostRepository;
import com.example.vezba1.repository.HostsPerCountryViewRepository;
import com.example.vezba1.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final HostRentalRepository hostRentalRepository;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository, CountryRepository countryRepository, HostRentalRepository hostRentalRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.hostRentalRepository = hostRentalRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id).map(x -> {
            if (host.getName() != null) {
                x.setName(host.getName());
            }
            if (host.getSurname() != null) {
                x.setSurname(host.getSurname());
            }
            if (host.getCountry() != null && countryRepository.findById(host.getCountry().getId()).isPresent()) {
                x.setCountry(countryRepository.findById(host.getCountry().getId()).get());

            }
            Host updatedHost = hostRepository.save(x);

            this.applicationEventPublisher.publishEvent(new HostCreatedEvent(host));
            return updatedHost;
        });
    }

    @Override
    public Optional<Host> save(Host host) {
        Optional<Host> savedHost = Optional.empty();
        if (host.getName() != null && host.getSurname() != null && host.getCountry() != null && countryRepository.findById(host.getCountry().getId()).isPresent()) {
            savedHost = Optional.of(hostRepository.save(new Host(
                    host.getName(),
                    host.getSurname(),
                    countryRepository.findById(host.getCountry().getId()).get()
            )));
            this.applicationEventPublisher.publishEvent(new HostCreatedEvent(host));
        }
        return savedHost;
    }


    @Override
    public void deleteById(Long id) {
        hostRepository.findById(id).ifPresent(host -> {
            hostRepository.deleteById(id);
            this.applicationEventPublisher.publishEvent(new HostCreatedEvent(host));
        });

    }

    @Override
    public Optional<Host> findMostPopularHost() {
        return hostRepository.findAll().stream()
                .max(Comparator.comparingLong(host -> hostRentalRepository.countByHostId(host.getId())));
    }

    @Override
    public void refreshMaterialized2() {
        hostsPerCountryViewRepository.refreshMaterialized2();
    }

    @Override
    public List<HostsPerCountryView> getAllHostPerCountry() {
        return hostsPerCountryViewRepository.findAll();
    }

    @Override
    public List<HostProjection> getAllHostNames() {
        return hostRepository.takeNameAndSurnameByProjection();
    }
}
