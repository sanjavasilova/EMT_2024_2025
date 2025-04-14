package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.DisplayAccommodationRentDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccommodationRentApplicationService {
    Optional<DisplayAccommodationRentDto> rentAccommodation(Long id);
}
