package com.example.vezba1.model.domain;

import com.example.vezba1.model.enumerations.CategoryType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.ORDINAL)
    private CategoryType categoryType;
    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;
    private Integer numRooms;
    private boolean isRented;

    public Accommodation(String name, CategoryType categoryType, Host host, Integer numRooms) {
        this.name = name;
        this.categoryType = categoryType;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented=false;
    }
    public Accommodation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }
}
