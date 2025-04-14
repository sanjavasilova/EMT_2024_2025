package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationService {
    Optional<TemporaryReservation> addToTemporaryReservation(User user, Long accommodationId);
    List<TemporaryReservation> getUserTemporaryReservations(User user);
    void confirmReservations(User user);
}
