package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {


    List<Commentaire> findByArticleId(Long articleId);

    List<Commentaire> findByUtilisateurId(Long utilisateurId);
}
