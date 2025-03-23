package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.AccommodationRent;
import mk.ukim.finki.emt.lab.repository.AccommodationRentRepository;
import mk.ukim.finki.emt.lab.service.AccommodationRentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccommodationRentServiceImpl implements AccommodationRentService{
    private final AccommodationRentRepository accommodationRentRepository;

    public AccommodationRentServiceImpl(AccommodationRentRepository accommodationRentRepository) {
        this.accommodationRentRepository = accommodationRentRepository;
    }

    @Override
    public Optional<AccommodationRent> rentAccommodation(Long id) {
        if (accommodationRentRepository.findById(id).isEmpty()){
            return Optional.empty();
        }
        AccommodationRent accommodationRent = accommodationRentRepository.findById(id).get();
        if (accommodationRent.isRent()){
            return Optional.empty();
        }
        accommodationRent.setRent(true);
        return Optional.of(accommodationRentRepository.save(accommodationRent));
    }
}
