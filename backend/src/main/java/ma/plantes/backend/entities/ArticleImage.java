package ma.plantes.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "article_images")
public class ArticleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "images_id")
    private Long id;

    private String imageUrl;
    private int width;
    private int heigth;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @JsonIgnoreProperties("images")
    private Article article;
}
