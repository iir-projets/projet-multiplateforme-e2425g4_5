package ma.plantes.backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentaireDTO {

    private String contenu;
    private Long articleId;
    private Long userId;
}
