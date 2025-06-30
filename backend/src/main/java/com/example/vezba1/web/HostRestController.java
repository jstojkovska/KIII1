package com.example.vezba1.web;

import com.example.vezba1.dto.CreateHostDto;
import com.example.vezba1.dto.DisplayHostDto;
import com.example.vezba1.dto.HostCountryStatsDto;
import com.example.vezba1.model.domain.Host;
import com.example.vezba1.model.projections.HostProjection;
import com.example.vezba1.service.application.HostApplicationService;
import com.example.vezba1.service.domain.HostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "Endpoints for managing hosts")
public class HostRestController {
    private final HostApplicationService hostApplicationService;

    public HostRestController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(summary = "Get all hosts", description = "Retrieves a list of all hosts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of hosts retrieved successfully")
    })
    @GetMapping
    public List<DisplayHostDto> findAll() {
        return hostApplicationService.findAll();
    }

    @Operation(summary = "Find host by ID", description = "Returns a host by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Host found"),
            @ApiResponse(responseCode = "404", description = "Host not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new host", description = "Adds a new host to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Host created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto hostDto) {
        return hostApplicationService.save(hostDto).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing host", description = "Updates a host's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Host updated successfully"),
            @ApiResponse(responseCode = "404", description = "Host not found")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto hostDto) {
        return hostApplicationService.update(id, hostDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host", description = "Deletes a host by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Host deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Host not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (hostApplicationService.findById(id).isPresent()) {
            hostApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/hosts/most-popular")
    public ResponseEntity<DisplayHostDto> getMostPopularHost() {
        return hostApplicationService.findMostPopularHost()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-country")
    public ResponseEntity<List<HostCountryStatsDto>> getAllHosts() {
        List<HostCountryStatsDto> list=hostApplicationService.getAllHostPerCountry();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/names")
    public ResponseEntity<List<HostProjection>> getAllHostNames() {
        List<HostProjection> list = hostApplicationService.getAllHostNames();
        return ResponseEntity.ok(list);
    }

}
