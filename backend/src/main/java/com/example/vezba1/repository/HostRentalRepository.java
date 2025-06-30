package com.example.vezba1.repository;

import com.example.vezba1.model.domain.HostRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRentalRepository extends JpaRepository<HostRental, Long> {
    long countByHostId(Long hostId);
}
