package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enumerations.AccommodationCategory;
import mk.ukim.finki.emt.lab.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDto(
        String name,
        AccommodationCategory category,
        String hostUsername,
        Integer numRooms
) {
    public static CreateAccommodationDto from(Accommodation accommodation) {
        return new CreateAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getUsername(),
                accommodation.getNumRooms()
        );
    }

    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(User host) {
        return new Accommodation(name, category,host,numRooms);
    }
}
