package com.example.vezba1.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private int price;
    @ManyToOne
    private Accommodation accommodation;

    public Availability(LocalDateTime start_time, LocalDateTime end_time, int price, Accommodation accommodation) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.price = price;
        this.accommodation = accommodation;
    }

    public Availability() {
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start) {
        this.start_time = start;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end) {
        this.end_time = end;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
