package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.AccommodationCategory;
import mk.ukim.finki.emt.lab.model.AccommodationRent;
import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.model.dto.AccommodationDto;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.service.AccommodationRentService;
import mk.ukim.finki.emt.lab.service.AccommodationService;
import mk.ukim.finki.emt.lab.service.HostService;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final AccommodationRentService accommodationRentService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, AccommodationRentService accommodationRentService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.accommodationRentService = accommodationRentService;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    public boolean isValidCategory(String category) {
        return Arrays.stream(AccommodationCategory.values())
                .anyMatch(e -> e.name().equals(category.toUpperCase()));

    }

    @Override
    public Optional<Accommodation> addAccommodation(AccommodationDto accommodation) {
        if (accommodation.getName() == null || accommodation.getName().isEmpty()) {
            return Optional.empty();
        }
        if (!isValidCategory(accommodation.getCategory())) {
            return Optional.empty();
        }
        AccommodationCategory category = AccommodationCategory.valueOf(accommodation.getCategory().toUpperCase());
        if (hostService.findById(accommodation.getHostId()).isEmpty()) {
            return Optional.empty();
        }
        Host host = hostService.findById(accommodation.getHostId()).get();
        if (accommodation.getNumRooms() == null || accommodation.getNumRooms() == 0) {
            return Optional.empty();
        }
        Accommodation accommodationToAdd = new Accommodation(accommodation.getName(), category, host, accommodation.getNumRooms());
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
    public Optional<Accommodation> editAccommodation(Long id, AccommodationDto accommodation) {
        if (findById(id).isEmpty()) {
            return Optional.empty();
        }
        Accommodation accommodationToEdit = findById(id).get();
        if (accommodation.getName() != null) {
            accommodationToEdit.setName(accommodation.getName());
        }
        if (accommodation.getCategory() != null && isValidCategory(accommodation.getCategory())) {
            accommodationToEdit.setCategory(AccommodationCategory.valueOf(accommodation.getCategory().toUpperCase()));
        }
        if (hostService.findById(accommodation.getHostId()).isPresent()) {
            accommodationToEdit.setHost(hostService.findById(accommodation.getHostId()).get());
        }
        if (accommodation.getNumRooms() != null && accommodation.getNumRooms() > 0) {
            accommodationToEdit.setNumRooms(accommodation.getNumRooms());
        }
        return Optional.of(accommodationRepository.save(accommodationToEdit));
    }

    @Override
    public Optional<AccommodationRent> rentAccommodation(Long id) {
        return accommodationRentService.rentAccommodation(id);
    }
}
