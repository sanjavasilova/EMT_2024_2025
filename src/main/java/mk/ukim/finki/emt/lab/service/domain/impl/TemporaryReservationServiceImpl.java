package mk.ukim.finki.emt.lab.service.domain.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.AccommodationRent;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.repository.AccommodationRentRepository;
import mk.ukim.finki.emt.lab.repository.TemporaryReservationRepository;
import mk.ukim.finki.emt.lab.service.domain.TemporaryReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TemporaryReservationServiceImpl implements TemporaryReservationService {

    private final TemporaryReservationRepository temporaryReservationRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationRentRepository accommodationRentRepository;

    @Override
    public Optional<TemporaryReservation> addToTemporaryReservation(User user, Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation doesn't exist"));

        List<TemporaryReservation> temporaryReservations = temporaryReservationRepository.findByAccommodationId(accommodationId);
        if ((temporaryReservations.size() + accommodationRentRepository.findByAccommodationId(accommodationId).size()) == accommodation.getNumRooms()) {
            return Optional.empty();
        }

        return Optional.of(temporaryReservationRepository.save(new TemporaryReservation(user, accommodation)));
    }

    @Override
    public List<TemporaryReservation> getUserTemporaryReservations(User user) {
        return temporaryReservationRepository.findByUser(user);
    }

    @Override
    public void confirmReservations(User user) {
        List<TemporaryReservation> reservations = temporaryReservationRepository.findByUser(user);

        for (TemporaryReservation res : reservations) {
            if (res.getAccommodation().getNumRooms() > accommodationRentRepository.findByAccommodation(res.getAccommodation()).size())
                accommodationRentRepository.save(new AccommodationRent(res.getAccommodation(), true));
        }

        temporaryReservationRepository.deleteAll(reservations);
    }
}
