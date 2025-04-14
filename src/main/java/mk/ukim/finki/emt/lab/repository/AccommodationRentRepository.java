package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.AccommodationRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.channels.FileChannel;
import java.util.List;

@Repository
public interface AccommodationRentRepository extends JpaRepository<AccommodationRent, Long> {
    List<AccommodationRent> findByAccommodationId(Long id);

    List<AccommodationRent> findByAccommodation(Accommodation accommodation);
}
