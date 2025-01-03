package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.ArticleDTO;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.repositories.ArticleEnregistreRepository;
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
        // Vérifiez si l'article existe déjà pour cet utilisateur
        Optional<Article> existingArticle = articleEnregistreRepository.findById(articleDTO.getId());

        if (existingArticle.isPresent()) {
            // Si l'article existe, mettez à jour l'état isSaved
            Article article = existingArticle.get();
            article.setIsSaved(true);
            articleEnregistreRepository.save(article);
        } else {
            // Sinon, créez un nouvel article
            Article article = new Article();
            article.setId(articleDTO.getId());
            article.setTitre(articleDTO.getTitre());
            article.setContenu(articleDTO.getContenu());
            article.setImage(articleDTO.getImage());
            article.setIsSaved(true);
            articleEnregistreRepository.save(article);
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
