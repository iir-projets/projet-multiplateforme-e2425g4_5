package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.ArticleEnregistreDTO;
import ma.plantes.backend.dto.FavorisDTO;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.ArticleEnregistre;
import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.repositories.ArticleEnregistreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
