package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayTemporaryReservationDto;
import mk.ukim.finki.emt.lab.service.application.TemporaryReservationApplicationService;
import mk.ukim.finki.emt.lab.service.domain.TemporaryReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {
    private final TemporaryReservationService temporaryReservationService;

    public TemporaryReservationApplicationServiceImpl(TemporaryReservationService temporaryReservationService) {
        this.temporaryReservationService = temporaryReservationService;
    }

    @Override
    public Optional<DisplayTemporaryReservationDto> addToTemporaryReservation(User user, Long accommodationId) {
        return temporaryReservationService.addToTemporaryReservation(user, accommodationId).map(DisplayTemporaryReservationDto::from);
    }

    @Override
    public List<DisplayTemporaryReservationDto> getUserTemporaryReservations(User user) {
        return DisplayTemporaryReservationDto.from(temporaryReservationService.getUserTemporaryReservations(user));
    }

    @Override
    public void confirmReservations(User user) {
        temporaryReservationService.confirmReservations(user);
    }
}
