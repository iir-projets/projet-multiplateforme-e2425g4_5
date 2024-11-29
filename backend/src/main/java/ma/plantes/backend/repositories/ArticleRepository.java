package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // Trouver un article par son titre (exact match)
    Optional<Article> findByTitre(String titre);

    // Trouver tous les articles d'un utilisateur spécifique
    List<Article> findByUserId(Long userId);

    // Trouver tous les articles associés à une plante spécifique
    List<Article> findByPlanteId(Long planteId);

    // Vérifier si un article avec un titre donné existe
    boolean existsByTitre(String titre);
}
