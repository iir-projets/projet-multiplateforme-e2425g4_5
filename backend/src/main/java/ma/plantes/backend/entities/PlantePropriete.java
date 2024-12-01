package ma.plantes.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plante_propriete")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlantePropriete {

    @EmbeddedId
    private PlanteProprieteId id;


    @ManyToOne
    @MapsId("planteId")
    @JoinColumn(name = "plante_id")
    private Plante plante;


    @ManyToOne
    @MapsId("proprieteId")
    @JoinColumn(name = "propriete_id")
    private Propriete propriete;

    // other fields and methods
}

