package ma.plantes.backend.service;

import ma.plantes.backend.entities.Commentaire;
import ma.plantes.backend.repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;

    @Autowired
    public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    // Ajouter un commentaire
    public Commentaire ajouterCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    // Supprimer un commentaire
    public boolean supprimerCommentaire(Long id) {
        if (commentaireRepository.existsById(id)) {
            commentaireRepository.deleteById(id);
            return true;
        }
        return false; // Le commentaire n'existe pas
    }

    // Trouver tous les commentaires par article
    public List<Commentaire> trouverCommentairesParArticle(Long articleId) {
        return commentaireRepository.findByArticleId(articleId);
    }

    // Trouver tous les commentaires par utilisateur
    public List<Commentaire> trouverCommentairesParUtilisateur(Long utilisateurId) {
        return commentaireRepository.findByUtilisateurId(utilisateurId);
    }
}
