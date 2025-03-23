package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.AccommodationRent;
import mk.ukim.finki.emt.lab.model.dto.AccommodationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> addAccommodation(AccommodationDto accommodation);

    void deleteById(Long id);

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> editAccommodation(Long id, AccommodationDto accommodation);

    Optional<AccommodationRent> rentAccommodation(Long id);
}
