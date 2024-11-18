package ma.plantes.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Plante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nom_plante")
    private String nom;

    @Column(nullable = true)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Propriete> propriete;

    @Column(nullable = false)
    private String region;

    @Column(nullable = true)
    private String precaution;

    @Column(nullable = true)
    private String interaction;

    @Column(nullable = false)
    private String image;
}
