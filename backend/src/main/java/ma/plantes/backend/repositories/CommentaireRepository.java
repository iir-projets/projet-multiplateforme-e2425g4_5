package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    // Trouver tous les commentaires associés à un article
    List<Commentaire> findByArticleId(Long articleId);

    // Trouver tous les commentaires associés à un utilisateur
    List<Commentaire> findByUtilisateurId(Long utilisateurId);

    // Supprimer un commentaire par son ID (fourni par JpaRepository)
    void deleteById(Long id);
}
