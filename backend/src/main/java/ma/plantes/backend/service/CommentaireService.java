package ma.plantes.backend.service;

import ma.plantes.backend.entities.Commentaire;
import ma.plantes.backend.repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;

    @Autowired
    public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    public Optional<Commentaire> getCommentaireById(Long id) {
        return commentaireRepository.findById(id);
    }

    public List<Commentaire> getCommentairesByArticleId(Long articleId) {
        return commentaireRepository.findByArticleId(articleId);
    }

    public List<Commentaire> getCommentairesByUtilisateurId(Long utilisateurId) {
        return commentaireRepository.findByUtilisateurId(utilisateurId);
    }

    public Commentaire createCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    public Commentaire updateCommentaire(Long id, Commentaire commentaire) {
        if (commentaireRepository.existsById(id)) {
            commentaire.setId(id);
            return commentaireRepository.save(commentaire);
        }
        return null;
    }

    public void deleteCommentaire(Long id) {
        if (commentaireRepository.existsById(id)) {
            commentaireRepository.deleteById(id);
        }
    }
}
