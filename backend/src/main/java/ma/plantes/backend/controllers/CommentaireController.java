package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.CommentaireDTO;
import ma.plantes.backend.entities.Commentaire;
import ma.plantes.backend.service.CommentaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaires")
@RequiredArgsConstructor
public class CommentaireController {

    private final CommentaireService commentaireService;


    // Ajouter un commentaire
    @PostMapping
    public ResponseEntity<Commentaire> ajouterCommentaire(@RequestBody CommentaireDTO commentaire) {
        Commentaire nouveauCommentaire = commentaireService.ajouterCommentaire(commentaire);
        return ResponseEntity.ok(nouveauCommentaire);
    }

    // Supprimer un commentaire par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerCommentaire(@PathVariable Long id) {
        boolean estSupprime = commentaireService.supprimerCommentaire(id);
        if (estSupprime) {
            return ResponseEntity.ok("Commentaire supprimé avec succès.");
        }
        return ResponseEntity.status(404).body("Commentaire non trouvé.");
    }

    // Récupérer tous les commentaires d'un article spécifique
    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<Commentaire>> getCommentairesParArticle(@PathVariable Long articleId) {
        List<Commentaire> commentaires = commentaireService.trouverCommentairesParArticle(articleId);
        return ResponseEntity.ok(commentaires);
    }


}
