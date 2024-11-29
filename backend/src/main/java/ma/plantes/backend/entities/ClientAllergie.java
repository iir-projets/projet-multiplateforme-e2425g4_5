package ma.plantes.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClientAllergie {

    @EmbeddedId
    private ClientAllergieId id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn( name = "id")
    private User user;

    @ManyToOne
    @MapsId("allergieId")
    private Allergie allergie;
}
