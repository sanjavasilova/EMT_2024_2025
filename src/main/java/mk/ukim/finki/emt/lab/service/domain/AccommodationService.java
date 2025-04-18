package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> addAccommodation(Accommodation accommodation);

    void deleteById(Long id);

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> editAccommodation(Long id, Accommodation accommodation);

    List<Accommodation> findByHost(User host);
}
