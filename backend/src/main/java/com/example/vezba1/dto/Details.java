package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.Country;
import com.example.vezba1.model.domain.Host;

public record Details(HostDetailsDto hostDetailsDto, CountryDetailsDto countryDetailsDto,
                      AccommodationsDetailsDto accommodationsDetailsDto) {
    public static Details from(Accommodation accommodation) {
        return new Details(new HostDetailsDto(accommodation.getHost().getName(), accommodation.getHost().getSurname(), accommodation.getHost().getCountry().getName(), accommodation.getHost().getCountry().getContinent()), new CountryDetailsDto(accommodation.getHost().getCountry().getName(), accommodation.getHost().getCountry().getContinent()), new AccommodationsDetailsDto(accommodation.getId(), accommodation.getName(), accommodation.getCategoryType().name(), accommodation.getNumRooms()));
    }
}
