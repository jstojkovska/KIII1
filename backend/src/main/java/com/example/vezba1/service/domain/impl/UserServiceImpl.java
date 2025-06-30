package com.example.vezba1.service.domain.impl;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.JwtTokenLog;
import com.example.vezba1.model.domain.User;
import com.example.vezba1.model.enumerations.Role;
import com.example.vezba1.model.exceptions.*;
import com.example.vezba1.repository.AccommodationRepository;
import com.example.vezba1.repository.JwtTokenLogRepository;
import com.example.vezba1.repository.UserRepository;
import com.example.vezba1.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccommodationRepository accommodationRepository;
    private final JwtTokenLogRepository jwtTokenLogRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AccommodationRepository accommodationRepository, JwtTokenLogRepository jwtTokenLogRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accommodationRepository = accommodationRepository;
        this.jwtTokenLogRepository = jwtTokenLogRepository;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new InvalidUserCredentialsException();
        return user;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Optional<User> addToTempReservations(String username, Long accommodationId) {
        User user = userRepository.findById(username).orElseThrow();
        Accommodation acc = accommodationRepository.findById(accommodationId).orElseThrow();
        if (acc.isRented()) {
            throw new RuntimeException("Accommodation is already rented");
        }

        user.getTempReservations().add(acc);
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<Accommodation> addTemp(String username, Long accommodationId) {
        User user = userRepository.findById(username).orElseThrow();
        Accommodation acc = accommodationRepository.findById(accommodationId).orElseThrow();
        if (acc.isRented()) {
            throw new RuntimeException("Accommodation is already rented");
        }

        user.getTempReservations().add(acc);
        return Optional.of(accommodationRepository.save(acc));
    }

    @Override
    public Optional<List<Accommodation>> getTempReservations(String username) {
        return userRepository.findByUsername(username).map(User::getTempReservations);
    }

    @Override
    public Optional<User> confirmAllReservations(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        user.getTempReservations().forEach(x -> x.setRented(true));
        accommodationRepository.saveAll(user.getTempReservations());
        user.getTempReservations().clear();
        return Optional.of(userRepository.save(user));
    }

    @Override
    public List<User> findAllWithoutTempReservations() {
        return userRepository.findAllWithoutTempReservations();
    }

    @Override
    public List<JwtTokenLog> getAllAuthLogs() {
        return jwtTokenLogRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
