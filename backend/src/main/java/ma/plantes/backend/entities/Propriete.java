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
public class Propriete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nom_propriete")
    private String nom;

    @ManyToMany(mappedBy = "propriete", fetch = FetchType.EAGER)
    private List<Plante> plantes;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "valeur_plante")
    private List<String> valeurs;
}
