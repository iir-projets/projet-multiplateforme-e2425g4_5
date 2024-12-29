package ma.plantes.backend.service;

import ma.plantes.backend.dto.CommentaireDTO;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.Commentaire;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.repositories.ArticleRepository;
import ma.plantes.backend.repositories.CommentaireRepository;
import ma.plantes.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentaireService(CommentaireRepository commentaireRepository, ArticleRepository articleRepository, UserRepository userRepository) {
        this.commentaireRepository = commentaireRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    // Ajouter un commentaire
    public Commentaire ajouterCommentaire(CommentaireDTO commentaire) {

        Commentaire c = new Commentaire();
        c.setContenu(commentaire.getContenu());
        Article article = articleRepository.findById(commentaire.getArticleId()).orElse(null);
        c.setArticle(article);
        User user = userRepository.findById(commentaire.getUserId()).orElse(null);
        c.setUtilisateur(user);
        return commentaireRepository.save(c);
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
