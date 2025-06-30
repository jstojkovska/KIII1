package com.example.vezba1.web;

import com.example.vezba1.dto.CreateAvailabilityDto;
import com.example.vezba1.dto.DisplayAvailabilityDto;
import com.example.vezba1.service.application.AvailabilityApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityRestController {
    private final AvailabilityApplicationService availabilityApplicationService;

    public AvailabilityRestController(AvailabilityApplicationService availabilityApplicationService) {
        this.availabilityApplicationService = availabilityApplicationService;
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAvailabilityDto> add(@RequestBody CreateAvailabilityDto createAvailabilityDto) {
        return availabilityApplicationService.createAvailability(createAvailabilityDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAvailabilityDto> getAvailability(@PathVariable Long id) {
        return availabilityApplicationService.getAvailabilityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<DisplayAvailabilityDto> getAllAvailabilities() {
        return availabilityApplicationService.getAllAvailabilities();
    }
}
