package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.ArticleEnregistre;

import ma.plantes.backend.entities.ArticleId;

import ma.plantes.backend.entities.ArticleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ArticleEnregistreRepository extends JpaRepository<ArticleEnregistre, ArticleId> {

    @Query(value = "SELECT a.id AS article_id, a.titre, COUNT(a.id) AS total " +
            "FROM article_enregistre ae " +
            "JOIN article a ON ae.article_id = a.id " +  // jointure sur l'ID de l'article
            "GROUP BY a.id, a.titre " +
            "ORDER BY total DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5ArticlesSaved();




    List<ArticleEnregistre> findAllByUserId(Long clientId);


    // Méthode pour trouver un article par clientId et articleId
    Optional<ArticleEnregistre> findByUserIdAndArticleId(Long clientId, Long articleId);

    @Modifying
    @Query("DELETE FROM ArticleEnregistre ae WHERE ae.id.clientId = :clientId AND ae.id.articleId = :articleId")
    void deleteByUserIdAndArticleId(@Param("clientId") Long clientId, @Param("articleId") Long articleId);


    Optional<ArticleEnregistre> findById(ArticleId id);


}
