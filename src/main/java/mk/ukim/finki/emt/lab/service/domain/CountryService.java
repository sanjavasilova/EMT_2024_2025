package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Country;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CountryService {
    Optional<Country> findById(Long id);
}
