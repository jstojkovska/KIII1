package com.example.vezba1.web;

import com.example.vezba1.dto.CreateReservationDto;
import com.example.vezba1.dto.DisplayReservationDto;
import com.example.vezba1.model.domain.Reservation;
import com.example.vezba1.service.application.ReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {
    private final ReservationApplicationService reservationApplicationService;

    public ReservationRestController(ReservationApplicationService reservationApplicationService) {
        this.reservationApplicationService = reservationApplicationService;
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayReservationDto> add(@RequestBody CreateReservationDto createReservationDto) {
        return reservationApplicationService.createReservation(createReservationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayReservationDto> getById(@PathVariable Long id) {
        return reservationApplicationService.findByIdReservation(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<DisplayReservationDto> getAll() {
        return reservationApplicationService.getAllReservations();
    }


}
