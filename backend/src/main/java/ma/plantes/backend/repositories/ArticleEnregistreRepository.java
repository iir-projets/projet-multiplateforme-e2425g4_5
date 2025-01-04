package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.ArticleEnregistre;
import ma.plantes.backend.entities.ArticleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleEnregistreRepository extends JpaRepository<ArticleEnregistre, ArticleId> {

    @Query(value = "SELECT a.article_id, COUNT(a.article_id) AS total " +
            "FROM article_enregistre a " +
            "GROUP BY a.article_id " +
            "ORDER BY total DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5ArticlesSaved();

    @Query("SELECT ae.article FROM ArticleEnregistre ae WHERE ae.user.id = :clientId")
    List<Article> findByClientId(@Param("clientId") Long clientId);

  //  Optional<Article> findById(Long id);



    Optional<ArticleEnregistre> findById(ArticleId id);

}
