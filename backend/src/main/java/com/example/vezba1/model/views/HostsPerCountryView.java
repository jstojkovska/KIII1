package com.example.vezba1.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Data
@Subselect(
        "SELECT * FROM public.hosts_per_country"
)
@Immutable
public class HostsPerCountryView {
    @Id
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "num_hosts")
    private Integer numHosts;

    public Long getCountryId() {
        return countryId;
    }

    public Integer getNumHosts() {
        return numHosts;
    }
}
