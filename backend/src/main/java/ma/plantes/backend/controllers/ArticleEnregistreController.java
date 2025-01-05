package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;

import ma.plantes.backend.dto.ArticleEnregistreDTO;
import ma.plantes.backend.dto.FavorisDTO;

import ma.plantes.backend.dto.ArticleDTO;

import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.ArticleEnregistre;
import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.service.ArticleEnregistreService;

import ma.plantes.backend.service.ArticleService;
import org.springframework.http.HttpStatus;
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


    @GetMapping("/savedarticle/byclient/{clientId}")
    public ResponseEntity<List<Article>> getSavedArticles(@PathVariable Long clientId) {
        List<Article> articles = articleEnregistreService.getSavedArticlesByClientId(clientId);
        return ResponseEntity.ok(articles);

    }

    // Afficher les top 5 articles
    @GetMapping("/admin/savedarticle/top5")
    public Map<String, Long> getTop5Articles() {
        return articleEnregistreService.getTop5Articles(); // Retourne une map avec nom de l'article et le total
    }
}
