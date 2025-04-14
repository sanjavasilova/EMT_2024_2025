package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.AccommodationRent;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationRentDto(
        Long accommodationId,
        boolean isRent
) {
    public static CreateAccommodationRentDto from(AccommodationRent accommodationRent) {
        return new CreateAccommodationRentDto(
                accommodationRent.getAccommodation().getId(),
                accommodationRent.isRent()
        );
    }

    public static List<CreateAccommodationRentDto> from(List<AccommodationRent> accommodationRents) {
        return accommodationRents.stream().map(CreateAccommodationRentDto::from).collect(Collectors.toList());
    }

    public AccommodationRent toAccommodation(Accommodation accommodation) {
        return new AccommodationRent(accommodation, isRent);
    }
}
