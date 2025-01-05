package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.ArticleEnregistreDTO;
import ma.plantes.backend.dto.FavorisDTO;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.ArticleEnregistre;
import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.repositories.ArticleEnregistreRepository;
import ma.plantes.backend.service.ArticleEnregistreService;
import ma.plantes.backend.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor

public class ArticleEnregistreController {

    private final ArticleEnregistreService articleEnregistreService;
    private final ArticleEnregistreRepository articleEnregistreRepository;

    @GetMapping("/admin/savedarticle/byclient/{clientId}")
    public List<ArticleEnregistreDTO> afficherArticleEnregistreByClient(@PathVariable Long clientId) {
        // Récupérer tous les favoris du client
        List<ArticleEnregistre> savedArticleList = articleEnregistreService.getAllArticleEnregistreByClient(clientId);

        // Convertir les favoris en DTO
        return articleEnregistreService.convertToArticleEnregistreDTO(savedArticleList);
    }

    // Afficher les top 5 articles
    @GetMapping("/admin/savedarticle/top5")
    public Map<String, Long> getTop5Articles() {
        List<Object[]> results = articleEnregistreRepository.findTop5ArticlesSaved();

        Map<String, Long> top5Articles = new LinkedHashMap<>();
        for (Object[] result : results) {
            String title = (String) result[1];  // Le titre de l'article est à l'index 1
            Long count = (Long) result[2];  // Le total est à l'index 2
            top5Articles.put(title, count);
        }
        return top5Articles;
    }

}
