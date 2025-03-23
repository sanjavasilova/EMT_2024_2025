package mk.ukim.finki.emt.lab.model.dto;

import lombok.Data;

@Data
public class AccommodationDto {
    private String name;

    private String category;

    private Long hostId;

    private Integer numRooms;
}
