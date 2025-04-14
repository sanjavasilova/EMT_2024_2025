package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TemporaryReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Accommodation accommodation;

    public TemporaryReservation() {}

    public TemporaryReservation(User user, Accommodation accommodation) {
        this.user = user;
        this.accommodation = accommodation;
    }
}

