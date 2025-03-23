package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.AccommodationRent;
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

    @GetMapping
    public List<DisplayAccommodationDto> getAllAccommodations() {
        return accommodationApplicationService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> addAccommodation(@RequestBody CreateAccommodationDto accommodation) {
        return accommodationApplicationService.addAccommodation(accommodation).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("isAuthenticated()")
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
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> editAccommodation(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodation) {
        return accommodationApplicationService.editAccommodation(id, accommodation).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationRentDto> rentAccommodation(@PathVariable Long id) {
        return accommodationRentApplicationService.rentAccommodation(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
