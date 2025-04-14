package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class AccommodationRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Accommodation accommodation;

    private boolean isRent = false;

    public AccommodationRent() {}

    public AccommodationRent(Accommodation accommodation, boolean isRent) {
        this.accommodation = accommodation;
        this.isRent = isRent;
    }
    public AccommodationRent(Accommodation accommodation) {
        this.accommodation = accommodation;
    }
}
