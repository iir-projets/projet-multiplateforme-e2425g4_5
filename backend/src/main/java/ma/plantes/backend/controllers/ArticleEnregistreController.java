package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.service.ArticleEnregistreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor

public class ArticleEnregistreController {

    private final ArticleEnregistreService articleEnregistreService;

    @GetMapping("/admin/savedarticle/byclient/{clientId}")
    public ResponseEntity<List<Article>> getSavedArticles(@PathVariable Long clientId) {
        List<Article> articles = articleEnregistreService.getSavedArticlesByClientId(clientId);
        return ResponseEntity.ok(articles);
    }

    // Afficher les top 5 articles
    @GetMapping("/admin/savedarticle/top5")
    public Map<Long, Long> getTop5Articles() {
        return articleEnregistreService.getTop5Articles();
    }
}
