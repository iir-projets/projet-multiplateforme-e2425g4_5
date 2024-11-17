package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {


    List<Article> findByPlanteId(Long planteId);


    List<Article> findByTitre(String titre);
}
