package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayTemporaryReservationDto(
        Long id,
        String userUsername,
        Long accommodationId
) {
    public static DisplayTemporaryReservationDto from(TemporaryReservation temporaryReservation) {
        return new DisplayTemporaryReservationDto(
                temporaryReservation.getId(),
                temporaryReservation.getUser().getUsername(),
                temporaryReservation.getAccommodation().getId()
        );
    }

    public static List<DisplayTemporaryReservationDto> from(List<TemporaryReservation> temporaryReservations) {
        return temporaryReservations.stream().map(DisplayTemporaryReservationDto::from).collect(Collectors.toList());
    }

    public TemporaryReservation toTemporaryReservation(User user, Accommodation accommodation) {
        return new TemporaryReservation(user, accommodation);
    }
}
