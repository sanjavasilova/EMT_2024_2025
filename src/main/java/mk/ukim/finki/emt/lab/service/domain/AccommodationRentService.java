package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.AccommodationRent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccommodationRentService {
    Optional<AccommodationRent> rentAccommodation(Long id);
}
