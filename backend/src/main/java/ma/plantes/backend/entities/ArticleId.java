package ma.plantes.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleId implements Serializable {

    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "article_id")
    private Long articleId;
}
