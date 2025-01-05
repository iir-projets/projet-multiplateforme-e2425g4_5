package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.ArticleEnregistreDTO;
import ma.plantes.backend.dto.FavorisDTO;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.ArticleEnregistre;
import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.service.ArticleEnregistreService;
import ma.plantes.backend.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor

public class ArticleEnregistreController {

    private final ArticleEnregistreService articleEnregistreService;

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
        return articleEnregistreService.getTop5Articles(); // Retourne une map avec nom de l'article et le total
    }
}
