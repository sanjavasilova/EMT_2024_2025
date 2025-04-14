package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.enumerations.AccommodationCategory;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> addAccommodation(Accommodation accommodation) {
        if (accommodation.getName() == null || accommodation.getName().isEmpty()) {
            return Optional.empty();
        }
        if (accommodation.getCategory() == null) {
            return Optional.empty();
        }
        AccommodationCategory category = accommodation.getCategory();
        if (accommodation.getHost() == null) {
            return Optional.empty();
        }
        if (accommodation.getNumRooms() == null || accommodation.getNumRooms() == 0) {
            return Optional.empty();
        }
        Accommodation accommodationToAdd = new Accommodation(accommodation.getName(), category, accommodation.getHost(), accommodation.getNumRooms());
        return Optional.of(accommodationRepository.save(accommodationToAdd));
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> editAccommodation(Long id, Accommodation accommodation) {
        if (findById(id).isEmpty()) {
            return Optional.empty();
        }
        Accommodation accommodationToEdit = findById(id).get();
        if (accommodation.getName() != null) {
            accommodationToEdit.setName(accommodation.getName());
        }
        if (accommodation.getCategory() != null) {
            accommodationToEdit.setCategory(accommodation.getCategory());
        }
        if (accommodation.getHost() != null) {
            accommodationToEdit.setHost(accommodation.getHost());
        }
        if (accommodation.getNumRooms() != null && accommodation.getNumRooms() > 0) {
            accommodationToEdit.setNumRooms(accommodation.getNumRooms());
        }
        return Optional.of(accommodationRepository.save(accommodationToEdit));
    }
}
