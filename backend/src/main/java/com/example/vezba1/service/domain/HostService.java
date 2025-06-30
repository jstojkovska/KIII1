package com.example.vezba1.service.domain;

import com.example.vezba1.dto.HostCountryStatsDto;
import com.example.vezba1.model.domain.Host;
import com.example.vezba1.model.projections.HostProjection;
import com.example.vezba1.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> update(Long id, Host host);
    Optional<Host> save(Host host);
    void deleteById(Long id);
    Optional<Host> findMostPopularHost();
    void refreshMaterialized2();
//    List<HostCountryStatsDto> getAllHostPerCountry();
    List<HostsPerCountryView> getAllHostPerCountry();
    List<HostProjection> getAllHostNames();

}
