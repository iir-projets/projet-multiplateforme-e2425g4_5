package ma.plantes.backend.controllers.REST;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    // Ajouter un article
    @PostMapping("/admin/articles")
    public ResponseEntity<Article> ajouterArticle(@RequestBody Article article) {
        Article newArticle = articleService.ajouterArticle(article);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }

    // Modifier un article
    @PutMapping("/admin/articles/{id}")
    public ResponseEntity<Article> modifierArticle(@PathVariable Long id, @RequestBody Article article) {
        Article updatedArticle = articleService.modifierArticle(id, article);
        if (updatedArticle == null) {
            return ResponseEntity.notFound().build(); // Article non trouvé pour modification
        }
        return ResponseEntity.ok(updatedArticle); // Article modifié avec succès
    }

    // Supprimer un article
    @DeleteMapping("/admin/articles/{id}")
    public ResponseEntity<String> supprimerArticle(@PathVariable Long id) {
        boolean isDeleted = articleService.supprimerArticle(id);

        if (isDeleted) {
            return ResponseEntity.ok("Article supprimé avec succès"); // Success response
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article non trouvé pour suppression"); // Failure response
        }
    }

    // Afficher tous les articles
    @GetMapping("/articles")
    public List<Article> afficherTousLesArticles() {
        return articleService.afficherTousLesArticles();
    }

    // Afficher un article par ID
    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> afficherArticleParId(@PathVariable Long id) {
        Optional<Article> article = articleService.afficherArticleParId(id);
        return article.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si l'article n'est pas trouvé, retourne 404
    }
}
