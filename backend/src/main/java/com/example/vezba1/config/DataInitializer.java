package com.example.vezba1.config;

import com.example.vezba1.model.domain.Accommodation;
import com.example.vezba1.model.domain.Country;
import com.example.vezba1.model.domain.Host;
import com.example.vezba1.model.domain.User;
import com.example.vezba1.model.enumerations.CategoryType;
import com.example.vezba1.model.enumerations.Role;
import com.example.vezba1.repository.AccommodationRepository;
import com.example.vezba1.repository.CountryRepository;
import com.example.vezba1.repository.HostRepository;
import com.example.vezba1.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccommodationRepository accommodationRepository;

    public DataInitializer(HostRepository hostRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, AccommodationRepository accommodationRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accommodationRepository = accommodationRepository;
    }

//    http://localhost:8080/swagger-ui/index.html

    @PostConstruct
    public void init() {

        Country c = new Country("C1", "C2");
        countryRepository.save(c);

        Host host = new Host("H1", "H2", c);
        hostRepository.save(host);

        accommodationRepository.save(new Accommodation("A1", CategoryType.ROOM, host, 1));
        accommodationRepository.save(new Accommodation("A3", CategoryType.ROOM, host, 1));

        userRepository.save(new User(
                "js",
                passwordEncoder.encode("js"),
                "Jana",
                "S",
                Role.ROLE_ADMIN));

        userRepository.save(new User(
                "kk",
                passwordEncoder.encode("kk"),
                "user",
                "user",
                Role.ROLE_USER
        ));

    }
}
