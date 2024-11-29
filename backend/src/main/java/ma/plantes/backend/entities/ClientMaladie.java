package ma.plantes.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientMaladie {

    @EmbeddedId
    private ClientMaladieId id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name="id")
    private User user;

    @ManyToOne
    @MapsId("maladieId")
    @JoinColumn(name="id")
    private Maladie maladie;
}
