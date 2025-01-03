package ma.plantes.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleEnregistre {

    @EmbeddedId //cle composite
    private ArticleId id;

    @Setter
    private boolean isSaved; // Champ isSaved

    // Si vous utilisez Lombok, @Getter et @Setter suffisent, sinon ajoutez manuellement les méthodes :

    public boolean getIsSaved() {
        return isSaved;
    }


    @ManyToOne
    @MapsId("clientId")
    @JoinColumn( name = "client_id")
    private User user;

    @ManyToOne
    @MapsId("articleId")
    @JoinColumn( name = "article_id")
    private Article article;








}
