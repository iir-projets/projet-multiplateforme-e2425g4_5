package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.ArticleDTO;
import ma.plantes.backend.dto.ArticleEnregistreDTO;
import ma.plantes.backend.entities.ArticleEnregistre;
import ma.plantes.backend.repositories.ArticleEnregistreRepository;
import ma.plantes.backend.service.ArticleEnregistreService;
import ma.plantes.backend.entities.Article;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;








@RestController
@RequiredArgsConstructor

public class ArticleEnregistreController {

    private final ArticleEnregistreService articleEnregistreService;
    private final ArticleEnregistreRepository articleEnregistreRepository;


    @GetMapping("/admin/savedarticle/byclient/{clientId}")
    public List<ArticleEnregistreDTO> afficherArticleEnregistreByClientForAdmin(@PathVariable Long clientId) {
        // Récupérer tous les favoris du client
        List<ArticleEnregistre> savedArticleList = articleEnregistreService.getAllArticleEnregistreByClient(clientId);

        // Convertir les favoris en DTO
        return articleEnregistreService.convertToArticleEnregistreDTO(savedArticleList);
    }

    @GetMapping("/savedarticle/byclient/{clientId}")
    public ResponseEntity<List<Article>> getSavedArticles(@PathVariable Long clientId) {
        List<Article> articles = articleEnregistreService.getSavedArticlesByClientId(clientId);
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/save_article")
    public ResponseEntity<String> saveArticle(@RequestBody ArticleDTO articleDTO) {
        try {
            // Appel à la méthode saveArticle du service pour enregistrer l'article
            articleEnregistreService.saveArticle(articleDTO);
            return ResponseEntity.ok("Article saved successfully");
        } catch (Exception e) {
            // En cas d'erreur, on retourne un message d'erreur avec un code 500
            return ResponseEntity.status(500).body("Error saving article: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete_article/byclient/{clientId}/article/{articleId}")
    public ResponseEntity<String> deleteArticleByClientIdAndArticleId(@PathVariable Long clientId, @PathVariable Long articleId) {
        try {
            // Appel à la méthode de suppression du service
            articleEnregistreService.supprimerArticleParClientIdEtArticleId(clientId, articleId);
            return ResponseEntity.ok("Article deleted successfully");
        } catch (Exception e) {
            // Si une erreur se produit, on retourne un message d'erreur
            return ResponseEntity.status(500).body("Error deleting article: " + e.getMessage());
        }
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
