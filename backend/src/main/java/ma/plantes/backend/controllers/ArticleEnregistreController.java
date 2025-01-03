package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.ArticleDTO;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.service.ArticleEnregistreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor

public class ArticleEnregistreController {

    private final ArticleEnregistreService articleEnregistreService;



    @PostMapping("/save-article")
    public ResponseEntity<String> saveArticle(@RequestBody ArticleDTO articleDTO) {
        try {
            articleEnregistreService.saveArticle(articleDTO);
            return ResponseEntity.ok("Article saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving article: " + e.getMessage());
        }
    }

    @GetMapping("/savedarticle/byclient/{clientId}")
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
