package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // Trouver un article par son titre (exact match)
    Optional<Article> findByTitre(String titre);


    // Vérifier si un article avec un titre donné existe
    boolean existsByTitre(String titre);
}
