package com.example.vezba1.service.domain;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.JwtTokenLog;
import com.example.vezba1.model.domain.User;
import com.example.vezba1.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    User login(String username,String password);
    User findByUsername(String username);
    Optional<User> addToTempReservations(String username, Long accommodationId);
    Optional<Accommodation> addTemp(String username, Long accommodationId);
    Optional<List<Accommodation>> getTempReservations(String username);
    Optional<User> confirmAllReservations(String username);
    public List<User> findAllWithoutTempReservations();
    List<JwtTokenLog> getAllAuthLogs();

}
