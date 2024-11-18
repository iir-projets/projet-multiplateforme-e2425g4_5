package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Commentaire;
import ma.plantes.backend.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    private final CommentaireService commentaireService;

    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @GetMapping
    public List<Commentaire> getAllCommentaires() {
        return commentaireService.getAllCommentaires();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commentaire> getCommentaireById(@PathVariable Long id) {
        Optional<Commentaire> commentaire = commentaireService.getCommentaireById(id);
        return commentaire.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/article/{articleId}")
    public List<Commentaire> getCommentairesByArticleId(@PathVariable Long articleId) {
        return commentaireService.getCommentairesByArticleId(articleId);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Commentaire> getCommentairesByUtilisateurId(@PathVariable Long utilisateurId) {
        return commentaireService.getCommentairesByUtilisateurId(utilisateurId);
    }

    @PostMapping
    public ResponseEntity<Commentaire> createCommentaire(@RequestBody Commentaire commentaire) {
        Commentaire createdCommentaire = commentaireService.createCommentaire(commentaire);
        return ResponseEntity.status(201).body(createdCommentaire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commentaire> updateCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaire) {
        Commentaire updatedCommentaire = commentaireService.updateCommentaire(id, commentaire);
        return updatedCommentaire != null ? ResponseEntity.ok(updatedCommentaire) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable Long id) {
        commentaireService.deleteCommentaire(id);
        return ResponseEntity.noContent().build();
    }
}
