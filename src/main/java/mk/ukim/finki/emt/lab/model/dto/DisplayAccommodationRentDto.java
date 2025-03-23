package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.AccommodationRent;
import mk.ukim.finki.emt.lab.model.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationRentDto(
        Long id,
        Long accommodationId,
        boolean isRent
) {
    public static DisplayAccommodationRentDto from(AccommodationRent accommodationRent) {
        return new DisplayAccommodationRentDto(
                accommodationRent.getId(),
                accommodationRent.getAccommodation().getId(),
                accommodationRent.isRent()
        );
    }

    public static List<DisplayAccommodationRentDto> from(List<AccommodationRent> accommodationRents) {
        return accommodationRents.stream().map(DisplayAccommodationRentDto::from).collect(Collectors.toList());
    }

    public AccommodationRent toAccommodation(Accommodation accommodation) {
        return new AccommodationRent(accommodation, isRent);
    }
}
