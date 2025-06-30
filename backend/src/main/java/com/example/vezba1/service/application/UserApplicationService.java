package com.example.vezba1.service.application;

import com.example.vezba1.dto.*;
import com.example.vezba1.model.domain.JwtTokenLog;
import com.example.vezba1.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);
    Optional<DisplayUserDto> findByUsername(String username);
    Optional<DisplayUserDto> addToTempReservation(String username, Long accommodationId);
    Optional<DisplayAccommodationDto> addTemp(String username, Long accommodationId);
    List<DisplayAccommodationDto> getTempReservations(String username);
    Optional<DisplayUserDto> confirmTempReservations(String username);
    public List<DisplayUserDto> findAllWithoutTempReservations();
    List<JwtTokenLogDto> getAllAuthLogs();
}
