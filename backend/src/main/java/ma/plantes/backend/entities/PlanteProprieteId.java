package ma.plantes.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanteProprieteId implements Serializable {

    @Column(name = "plante_id")
    private Long planteId;

    @Column(name = "propriete_id")
    private Long proprieteId;

    // Default constructor



}

