package com.example.vezba1.repository;

import com.example.vezba1.dto.CategoryStatsDto;
import com.example.vezba1.dto.Details;
import com.example.vezba1.dto.HostStatsDto;
import com.example.vezba1.model.domain.Accommodation;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    @Query("SELECT new com.example.vezba1.dto.CategoryStatsDto(a.categoryType, COUNT(a)) " +
            "FROM Accommodation a " +
            "WHERE a.isRented = false " +
            "GROUP BY a.categoryType")
    List<CategoryStatsDto> getAvailableCountByCategory();
//    @EntityGraph(attributePaths = {"host", "host.country"})
//    Optional<Details> findById(Long id);

}
