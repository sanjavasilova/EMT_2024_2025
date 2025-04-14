package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.dto.DisplayTemporaryReservationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TemporaryReservationApplicationService {
    Optional<DisplayTemporaryReservationDto> addToTemporaryReservation(User user, Long accommodationId);
    List<DisplayTemporaryReservationDto> getUserTemporaryReservations(User user);
    void confirmReservations(User user);
}
