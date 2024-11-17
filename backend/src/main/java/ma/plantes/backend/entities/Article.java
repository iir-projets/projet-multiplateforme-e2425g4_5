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
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "titre")
    private String titre;

    @Column(nullable = false, name = "contenu")
    private String contenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plante_id", nullable = false)
    private Plante plante;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commentaire> commentaires;

}
