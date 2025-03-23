package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.AccommodationRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRentRepository extends JpaRepository<AccommodationRent, Long> {
    List<AccommodationRent> findByAccommodationId(Long id);
}
