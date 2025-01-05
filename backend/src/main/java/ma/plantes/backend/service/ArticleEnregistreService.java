package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;

import ma.plantes.backend.dto.ArticleEnregistreDTO;
import ma.plantes.backend.dto.FavorisDTO;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.ArticleEnregistre;
import ma.plantes.backend.entities.Favoris;

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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleEnregistreService {

    private final ArticleEnregistreRepository articleEnregistreRepository;

    public List<ArticleEnregistre> getAllArticleEnregistreByClient(Long clientId) {
        return articleEnregistreRepository.findAllByUserId(clientId);
    }

    // Method to convert Favoris entities to DTOs
    public List<ArticleEnregistreDTO> convertToArticleEnregistreDTO(List<ArticleEnregistre> savedArticleList) {
        List<ArticleEnregistreDTO> response = new ArrayList<>();
        for (ArticleEnregistre saved : savedArticleList) {
            ArticleEnregistreDTO dto = new ArticleEnregistreDTO(
                    saved.getUser().getId(),
                    saved.getUser().getUsername(),
                    saved.getArticle().getId(),
                    saved.getArticle().getTitre()
            );
            response.add(dto);
        }
        return response;
    }



    public Map<String, Long> getTop5Articles() {

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
    @Transactional
    public void supprimerArticleParClientIdEtArticleId(Long clientId, Long articleId) {
        ArticleId articleIdComposite = new ArticleId(clientId, articleId);
        Optional<ArticleEnregistre> articleEnregistre = articleEnregistreRepository.findById(articleIdComposite);

        if (articleEnregistre.isPresent()) {
            articleEnregistreRepository.deleteByUserIdAndArticleId(clientId, articleId);
        } else {
            throw new RuntimeException("Article not found for clientId: " + clientId + " and articleId: " + articleId);
        }
    }



    public Map<Long, Long> getTop5Articles() {

        List<Object[]> results = articleEnregistreRepository.findTop5ArticlesSaved();
        Map<String, Long> topArticles = new LinkedHashMap<>();

        for (Object[] result : results) {
            String articleName = (String) result[1]; // Récupère le nom de l'article
            Long count = ((Number) result[2]).longValue(); // Récupère le nombre total
            topArticles.put(articleName, count); // Ajoute le nom de l'article et le count dans la map
        }

        return topArticles;
    }


}
