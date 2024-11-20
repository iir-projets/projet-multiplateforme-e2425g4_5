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
public class Favoris {

    @EmbeddedId //cle composite
    private FavorisId id;

    @ManyToOne
    @MapsId("utilisateurId")
    @JoinColumn( name = "utilisateur_id")
    private User user;

    @ManyToOne
    @MapsId("planteId")
    @JoinColumn( name = "plante_id")
    private Plante plante;






}
