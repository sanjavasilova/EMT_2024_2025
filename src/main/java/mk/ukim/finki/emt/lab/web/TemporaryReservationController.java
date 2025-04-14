package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.dto.DisplayTemporaryReservationDto;
import mk.ukim.finki.emt.lab.service.application.TemporaryReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/temporary-rent")
@Tag(name = "Temporary Reservation Controller", description = "Handles temporary accommodation reservations and confirmation.")
public class TemporaryReservationController {
    private final TemporaryReservationApplicationService temporaryReservationApplicationService;

    public TemporaryReservationController(TemporaryReservationApplicationService temporaryReservationApplicationService) {
        this.temporaryReservationApplicationService = temporaryReservationApplicationService;
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Add accommodation to temporary reservation", description = "Temporarily reserves an accommodation for the authenticated user.")
    @PostMapping("/reserve/{id}")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Reservation added successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid id or accommodation not available")}
    )
    public ResponseEntity<DisplayTemporaryReservationDto> addToTempReservation(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return temporaryReservationApplicationService.addToTemporaryReservation(user, id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get user's temporary reservations", description = "Retrieves a list of all temporary reservations for the authenticated user.")
    @GetMapping("/reserve/list")
    public List<DisplayTemporaryReservationDto> getTempReservations(@AuthenticationPrincipal User user) {
        return temporaryReservationApplicationService.getUserTemporaryReservations(user);
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Confirm user's reservations", description = "Confirms all temporary reservations and creates final bookings for the authenticated user.")
    @PostMapping("/reserve/confirm")
    public ResponseEntity<String> confirmReservations(@AuthenticationPrincipal User user) {
        temporaryReservationApplicationService.confirmReservations(user);
        return ResponseEntity.ok("The reservations have been confirmed.");
    }
}
