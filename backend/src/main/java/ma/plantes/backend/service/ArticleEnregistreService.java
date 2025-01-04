package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.ArticleDTO;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.ArticleEnregistre;
import ma.plantes.backend.entities.ArticleId;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.repositories.ArticleEnregistreRepository;
import ma.plantes.backend.repositories.ArticleRepository;
import ma.plantes.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleEnregistreService {

    private final ArticleEnregistreRepository articleEnregistreRepository;


    public void saveArticle(ArticleDTO articleDTO) {
        // Créez l'ID composite à partir des valeurs clientId et articleId
        ArticleId articleId = new ArticleId(articleDTO.getClientId(), articleDTO.getArticleId());

        // Vérifiez si l'enregistrement existe déjà
        Optional<ArticleEnregistre> existingArticleEnregistre = articleEnregistreRepository.findById(articleId);

        if (existingArticleEnregistre.isPresent()) {
            // Si l'article est déjà enregistré, on met à jour son statut
            ArticleEnregistre articleEnregistre = existingArticleEnregistre.get();
            articleEnregistre.setIsSaved(true); // Marque comme "sauvé"
            articleEnregistreRepository.save(articleEnregistre);
        } else {
            // Sinon, on crée un nouvel enregistrement pour l'article
            ArticleEnregistre articleEnregistre = new ArticleEnregistre();
            articleEnregistre.setId(articleId);  // Utilisation de l'ID composite
            articleEnregistre.setIsSaved(true);   // On marque l'article comme "sauvé"

            // Créez les entités Article et User associées
            Article article = new Article();
            article.setId(articleDTO.getArticleId());  // L'ID de l'article doit exister dans la base de données
            articleEnregistre.setArticle(article);

            User user = new User();
            user.setId(articleDTO.getClientId());  // L'ID du client doit exister dans la base de données
            articleEnregistre.setUser(user);

            // Sauvegarde de l'article dans la base de données
            articleEnregistreRepository.save(articleEnregistre);
        }
    }
    public Map<Long, Long> getTop5Articles() {
        List<Object[]> results = articleEnregistreRepository.findTop5ArticlesSaved();
        Map<Long, Long> topPlantes = new LinkedHashMap<>();

        for (Object[] result : results) {
            Long articleId = ((Number) result[0]).longValue();
            Long count = ((Number) result[1]).longValue();
            topPlantes.put(articleId, count);
        }

        return topPlantes;
    }

    public List<Article> getSavedArticlesByClientId(Long clientId) {
        return articleEnregistreRepository.findByClientId(clientId);
    }
}
