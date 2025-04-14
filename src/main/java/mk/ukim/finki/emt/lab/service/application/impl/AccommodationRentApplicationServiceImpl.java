package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.DisplayAccommodationRentDto;
import mk.ukim.finki.emt.lab.service.application.AccommodationRentApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationRentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccommodationRentApplicationServiceImpl implements AccommodationRentApplicationService {
    private final AccommodationRentService accommodationRentService;

    public AccommodationRentApplicationServiceImpl(AccommodationRentService accommodationRentService) {
        this.accommodationRentService = accommodationRentService;
    }

    @Override
    public Optional<DisplayAccommodationRentDto> rentAccommodation(Long id) {
        return accommodationRentService.rentAccommodation(id).map(DisplayAccommodationRentDto::from);
    }
}
