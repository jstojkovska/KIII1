package com.example.vezba1.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Getter
@Data
@Entity
@Subselect(
        "SELECT * FROM public.accommodations_per_host"
)
@Immutable
public class AccommodationsPerHostView {
    @Id
    @Column(name = "host_Id")
    private Long hostId;
    @Column(name = "num_accommodations")
    private Integer numAccommodations;

    public Long getHostId() {
        return hostId;
    }

    public Integer getNumAccommodations() {
        return numAccommodations;
    }
}
