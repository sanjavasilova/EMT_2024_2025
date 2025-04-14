package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mk.ukim.finki.emt.lab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationRentDto;
import mk.ukim.finki.emt.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab.service.application.AccommodationRentApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;
    private final AccommodationRentApplicationService accommodationRentApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService, AccommodationRentApplicationService accommodationRentApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
        this.accommodationRentApplicationService = accommodationRentApplicationService;
    }

    @Operation(summary = "Get all accommodations", description = "Retrieve a list of all available accommodations")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved accommodations")
    @GetMapping
    public List<DisplayAccommodationDto> getAllAccommodations() {
        return accommodationApplicationService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Add new accommodation", description = "Create a new accommodation")
    @ApiResponse(responseCode = "200", description = "Successfully added the accommodation")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> addAccommodation(@RequestBody CreateAccommodationDto accommodation) {
        return accommodationApplicationService.addAccommodation(accommodation).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Delete an accommodation", description = "Delete an accommodation by ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the accommodation")
    @ApiResponse(responseCode = "404", description = "Accommodation not found")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        if(accommodationApplicationService.findById(id).isPresent()) {
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Edit an accommodation", description = "Edit an existing accommodation by ID")
    @ApiResponse(responseCode = "200", description = "Successfully edited the accommodation")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> editAccommodation(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodation) {
        return accommodationApplicationService.editAccommodation(id, accommodation).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Rent an accommodation", description = "Rent an accommodation by ID")
    @ApiResponse(responseCode = "200", description = "Successfully rented the accommodation")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationRentDto> rentAccommodation(@PathVariable Long id) {
        return accommodationRentApplicationService.rentAccommodation(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
