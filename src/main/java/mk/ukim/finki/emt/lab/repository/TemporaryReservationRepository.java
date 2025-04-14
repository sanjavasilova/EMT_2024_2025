package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    List<TemporaryReservation> findByUser(User user);
    boolean existsByUserAndAccommodation(User user, mk.ukim.finki.emt.lab.model.domain.Accommodation accommodation);

    List<TemporaryReservation> findByAccommodationId(Long accommodationId);
}
