package com.example.vezba1.model.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class HostRental{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Host host;

        private LocalDateTime rentalDate;

        public HostRental() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Host getHost() {
                return host;
        }

        public void setHost(Host host) {
                this.host = host;
        }

        public LocalDateTime getRentalDate() {
                return rentalDate;
        }

        public void setRentalDate(LocalDateTime rentalDate) {
                this.rentalDate = rentalDate;
        }
}
