package com.example.vezba1.dto;

import com.example.vezba1.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto(Long id, String name, String surname, Long country) {
    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(host.getId(), host.getName(), host.getSurname(),host.getCountry().getId());
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts
                .stream()
                .map(DisplayHostDto::from)
                .collect(Collectors.toList());
    }
}
