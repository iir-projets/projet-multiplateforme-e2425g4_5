package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Commentaire;
import ma.plantes.backend.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    private final CommentaireService commentaireService;

    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    // Ajouter un commentaire
    @PostMapping
    public ResponseEntity<Commentaire> ajouterCommentaire(@RequestBody Commentaire commentaire) {
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

    // Récupérer tous les commentaires d'un utilisateur spécifique
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Commentaire>> getCommentairesParUtilisateur(@PathVariable Long utilisateurId) {
        List<Commentaire> commentaires = commentaireService.trouverCommentairesParUtilisateur(utilisateurId);
        return ResponseEntity.ok(commentaires);
    }
}
