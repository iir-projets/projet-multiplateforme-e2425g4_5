package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.ArticleEnregistre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleEnregistreRepository extends JpaRepository<ArticleEnregistre, Long> {

    @Query(value = "SELECT a.id AS article_id, a.titre, COUNT(a.id) AS total " +
            "FROM article_enregistre ae " +
            "JOIN article a ON ae.article_id = a.id " +  // jointure sur l'ID de l'article
            "GROUP BY a.id, a.titre " +
            "ORDER BY total DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5ArticlesSaved();




    List<ArticleEnregistre> findAllByUserId(Long clientId);
}
