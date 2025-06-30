package com.example.vezba1.repository;

import com.example.vezba1.model.domain.Host;
import com.example.vezba1.model.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    //    @Query("select h.name,h.surname from Host h")
    @Query("select h from Host h")
    List<HostProjection> takeNameAndSurnameByProjection();
}
