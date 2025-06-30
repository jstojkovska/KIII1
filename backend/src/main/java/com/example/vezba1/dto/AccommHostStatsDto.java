package com.example.vezba1.dto;

import com.example.vezba1.model.views.AccommodationsPerHostView;

public record AccommHostStatsDto(Long hostId, Long numAccommodations) {
    public static AccommHostStatsDto from(AccommodationsPerHostView view) {
        return new AccommHostStatsDto(view.getHostId(), view.getNumAccommodations().longValue());
    }
}
