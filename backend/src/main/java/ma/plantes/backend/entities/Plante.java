package ma.plantes.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Plante {

    @Getter
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



    @JsonIgnoreProperties("plante")

    @ManyToMany
    @JoinTable(
            name = "plante_propriete",
            joinColumns = @JoinColumn(name = "plante_id"),
            inverseJoinColumns = @JoinColumn(name = "propriete_id")
    )
    private List<Propriete> proprietes = new ArrayList<>();

    public String getName() {
        return nom;
    }

}
