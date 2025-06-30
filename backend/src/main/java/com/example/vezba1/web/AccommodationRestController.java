package com.example.vezba1.web;

import com.example.vezba1.dto.*;
import com.example.vezba1.model.enumerations.CategoryType;
import com.example.vezba1.service.application.AccommodationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationRestController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationRestController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @Operation(summary = "Get all accommodations", description = "Returns a list of all accommodations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodations retrieved successfully")
    })
    @GetMapping
    public List<DisplayAccommodationDto> findAll() {
        return accommodationApplicationService.findAll();
    }

    @Operation(summary = "Get accommodation by ID", description = "Returns accommodation by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation found"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return accommodationApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new accommodation", description = "Adds a new accommodation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto accommodationDto) {
        return accommodationApplicationService.save(accommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update accommodation", description = "Edits an existing accommodation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation updated successfully"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@RequestBody CreateAccommodationDto accommodationDto, @PathVariable Long id) {
        return accommodationApplicationService.update(id, accommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete accommodation", description = "Deletes an accommodation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationApplicationService.findById(id).isPresent()) {
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Mark accommodation as rented", description = "Marks an accommodation as rented")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation marked as rented"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @PostMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDto> markedAsRented(@PathVariable Long id) {
        return accommodationApplicationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/toggle-rented/{id}")
    public ResponseEntity<DisplayAccommodationDto> toggleRented(@PathVariable Long id){
        return accommodationApplicationService.toggleRented(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/most-popular")
//    public ResponseEntity<DisplayAccommodationDto> getMostPopular() {
//        return accommodationApplicationService.theMostPopular()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/accommodations/most-popular")
    public ResponseEntity<DisplayAccommodationDto> getMostPopularAccommodation() {
        return accommodationApplicationService.findMostPopularAccommodation()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stats/java/categories")
    public ResponseEntity<List<CategoryStatsDto>> getAllCategoriesJavaLogic() {
        List<CategoryStatsDto> categoryStatsDtos = accommodationApplicationService.getAvailableCountByCategoryUsingJavaLogic();
        return ResponseEntity.ok(categoryStatsDtos);
    }

    @GetMapping("/stats/categories")
    public ResponseEntity<List<CategoryStatsDto>> getAllCategories() {
        List<CategoryStatsDto> stats = accommodationApplicationService.getAvailableCountByCategory();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/by-host")
    public ResponseEntity<List<AccommHostStatsDto>> getAllAccommPerHost() {
        List<AccommHostStatsDto> stats = accommodationApplicationService.getAccommodationCountPerHost();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Details> getAllAccommodationsDetails(@PathVariable Long id) {
        return accommodationApplicationService.getDetailsById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/categories")
    public ResponseEntity<CategoryType[]> getAllCategoryTypes() {
        return ResponseEntity.ok(CategoryType.values());
    }
}

