package com.example.vezba1.web;

import com.example.vezba1.dto.*;
import com.example.vezba1.model.domain.User;
import com.example.vezba1.model.exceptions.InvalidArgumentsException;
import com.example.vezba1.model.exceptions.InvalidUserCredentialsException;
import com.example.vezba1.model.exceptions.PasswordsDoNotMatchException;
import com.example.vezba1.model.exceptions.UsernameAlreadyExistsException;
import com.example.vezba1.service.application.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
            responseCode = "400",
            description = "Invalid input or passwords do not match"
    )
    })
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "User authenticated successfully"
            ), @ApiResponse(
                    responseCode = "404", description = "Invalid username or password"
            )}
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(InvalidUserCredentialsException::new);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @Operation(
            summary = "Add accommodation to temporary reservations but here user is shown",
            description = "Adds a specified accommodation to the user's temporary reservations list."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation added successfully to the temporary reservation list."),
            @ApiResponse(responseCode = "400", description = "Invalid accommodation ID or other bad request.")
    })
    @PostMapping("/temp/add/{accommodationId}")
    public ResponseEntity<DisplayUserDto> addToTemp(@PathVariable Long accommodationId, Principal principal) {
        return userApplicationService.addToTempReservation(principal.getName(), accommodationId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Add accommodation to temporary reservations but here the accommodation is shown",
            description = "Adds a specified accommodation to the user's temporary reservations list."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation added successfully to the temporary reservation list."),
            @ApiResponse(responseCode = "400", description = "Invalid accommodation ID or other bad request.")
    })
    @PostMapping("/add/acc/{id}")
    public ResponseEntity<DisplayAccommodationDto> addTemp(@PathVariable Long id, Principal principal) {
        return userApplicationService.addTemp(principal.getName(), id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get all temporary reservations of the user",
            description = "Retrieves the list of temporary reservations made by the user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of temporary reservations retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "No temporary reservations found for the user.")
    })
    @GetMapping("/temp")
    public ResponseEntity<List<DisplayAccommodationDto>> getTempReservations(Principal principal) {
        return ResponseEntity.ok(userApplicationService.getTempReservations(principal.getName()));
    }

    @Operation(
            summary = "Confirm all temporary reservations",
            description = "Confirms all temporary reservations, marking them as rented."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All temporary reservations confirmed successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to confirm reservations.")
    })
    @PostMapping("/temp/confirm")
    public ResponseEntity<DisplayUserDto> confirmReservations(Principal principal) {
        return userApplicationService.confirmTempReservations(principal.getName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/all-basic")
    public ResponseEntity<List<DisplayUserDto>> findAllWithoutTempReservations() {
        return ResponseEntity.ok(userApplicationService.findAllWithoutTempReservations());
    }

    @GetMapping("/auth-logs")
    public ResponseEntity<List<JwtTokenLogDto>> getAllAuthLogs() {
        return ResponseEntity.ok(userApplicationService.getAllAuthLogs());
    }
}
