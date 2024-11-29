package ma.plantes.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "plante_propriete",
            joinColumns = @JoinColumn(name = "plante_id"),
            inverseJoinColumns = @JoinColumn(name = "propriete_id")
    )
    private List<Propriete> proprietes;

    @Column(nullable = false)
    private String region;

    @Column(nullable = true)
    private String precaution;

    @Column(nullable = true)
    private String interaction;

    private String image;
}
