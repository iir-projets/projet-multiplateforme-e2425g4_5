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
public class ClientMedicament {

    @EmbeddedId
    private ClientMedicamentId id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name="id")
    private User user;

    @ManyToOne
    @MapsId("medicamentId")
    @JoinColumn(name="id")
    private Medicament medicament;
}
