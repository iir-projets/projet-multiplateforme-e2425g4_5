package ma.plantes.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ArticleEnregistreDTO {
    @JsonProperty("clientId")
    private Long clientId;

    @JsonProperty("emailUser")
    private String clientEmail;

    @JsonProperty("articleId")
    private Long articleId;

    @JsonProperty("titreArticle")
    private String titreArticle;

    // Constructor that matches the arguments in the convertToFavorisDTO method
    public ArticleEnregistreDTO(Long clientId, String clientEmail, Long articleId, String titreArticle) {
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.articleId = articleId;
        this.titreArticle = titreArticle;
    }
}
