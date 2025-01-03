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


    @ManyToOne
    @MapsId("clientId")
    @JoinColumn( name = "client_id")
    private User user;

    @ManyToOne
    @MapsId("articleId")
    @JoinColumn( name = "article_id")
    private Article article;








}
