package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleEnregistreRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT a.article_id, COUNT(a.article_id) AS total " +
            "FROM article_enregistre a " +
            "GROUP BY a.article_id " +
            "ORDER BY total DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5ArticlesSaved();
}
