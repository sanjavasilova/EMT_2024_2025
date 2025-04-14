package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.AccommodationRent;
import mk.ukim.finki.emt.lab.repository.AccommodationRentRepository;
import mk.ukim.finki.emt.lab.service.domain.AccommodationRentService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationRentServiceImpl implements AccommodationRentService {
    private final AccommodationRentRepository accommodationRentRepository;
    private final AccommodationService accommodationService;

    public AccommodationRentServiceImpl(AccommodationRentRepository accommodationRentRepository, AccommodationService accommodationService) {
        this.accommodationRentRepository = accommodationRentRepository;
        this.accommodationService = accommodationService;
    }

    @Override
    public Optional<AccommodationRent> rentAccommodation(Long id) {
        Optional<Accommodation> optionalAccommodation = accommodationService.findById(id);

        if (optionalAccommodation.isEmpty()) {
            return Optional.empty();
        }

        Accommodation accommodation = optionalAccommodation.get();

        if (accommodation.getNumRooms() > accommodationRentRepository.findByAccommodation(accommodation).size()) {
                return Optional.of(accommodationRentRepository.save(new AccommodationRent(accommodation, true)));
        }
        return Optional.empty();
    }
}
