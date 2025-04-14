package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab.dto.DisplayAccommodationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> addAccommodation(CreateAccommodationDto createAccommodationDto);

    void deleteById(Long id);

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> editAccommodation(Long id, CreateAccommodationDto createAccommodationDto);
}
