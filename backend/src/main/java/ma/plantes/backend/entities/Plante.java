package ma.plantes.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Plante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nom_plante")
    private String nom;

    @Column(nullable = true, name = "description_plante")
    private String description;

    @Column(nullable = false)
    private String region;

    @Column(nullable = true)
    private String precaution;

    @Column(nullable = true)
    private String interaction;

    private String image;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "plante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlantePropriete> proprietes;


}
