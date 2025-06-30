package com.example.vezba1.dto;

import com.example.vezba1.model.views.HostsPerCountryView;

public record HostCountryStatsDto(Long countryId, Long numHosts) {
    public static HostCountryStatsDto from(HostsPerCountryView view){
        return new HostCountryStatsDto(view.getCountryId(), view.getNumHosts().longValue());
    }
}
