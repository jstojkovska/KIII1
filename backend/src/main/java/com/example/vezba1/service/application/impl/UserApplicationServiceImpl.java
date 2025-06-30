package com.example.vezba1.service.application.impl;

import com.example.vezba1.dto.*;
import com.example.vezba1.model.domain.User;
import com.example.vezba1.security.JwtHelper;
import com.example.vezba1.service.application.UserApplicationService;
import com.example.vezba1.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }


    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> addToTempReservation(String username, Long accommodationId) {
        return userService.addToTempReservations(username, accommodationId).map(DisplayUserDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> addTemp(String username, Long accommodationId) {
        return userService.addTemp(username, accommodationId).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<DisplayAccommodationDto> getTempReservations(String username) {
        return userService.getTempReservations(username)
                .orElse(List.of())
                .stream()
                .map(DisplayAccommodationDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayUserDto> confirmTempReservations(String username) {
        return userService.confirmAllReservations(username).map(DisplayUserDto::from);
    }

    @Override
    public List<DisplayUserDto> findAllWithoutTempReservations() {
        return userService.findAllWithoutTempReservations()
                .stream()
                .map(DisplayUserDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<JwtTokenLogDto> getAllAuthLogs() {
        return userService.getAllAuthLogs()
                .stream()
                .map(JwtTokenLogDto::from)
                .collect(Collectors.toList());
    }
}
