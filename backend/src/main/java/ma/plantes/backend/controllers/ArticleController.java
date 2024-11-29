package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Article;
import ma.plantes.backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Ajouter un article
    @PostMapping
    public ResponseEntity<Article> ajouterArticle(@RequestBody Article article) {
        Article newArticle = articleService.ajouterArticle(article);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }

    // Modifier un article
    @PutMapping("/{id}")
    public ResponseEntity<Article> modifierArticle(@PathVariable Long id, @RequestBody Article article) {
        Article updatedArticle = articleService.modifierArticle(id, article);
        if (updatedArticle == null) {
            return ResponseEntity.notFound().build(); // Article non trouvé pour modification
        }
        return ResponseEntity.ok(updatedArticle); // Article modifié avec succès
    }

    // Supprimer un article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerArticle(@PathVariable Long id) {
        boolean deleted = articleService.supprimerArticle(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Article supprimé
        }
        return ResponseEntity.notFound().build(); // Article non trouvé pour suppression
    }

    // Afficher tous les articles
    @GetMapping
    public List<Article> afficherTousLesArticles() {
        return articleService.afficherTousLesArticles();
    }

    // Afficher un article par ID
    @GetMapping("/{id}")
    public ResponseEntity<Article> afficherArticleParId(@PathVariable Long id) {
        Optional<Article> article = articleService.afficherArticleParId(id);
        return article.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si l'article n'est pas trouvé, retourne 404
    }
}
