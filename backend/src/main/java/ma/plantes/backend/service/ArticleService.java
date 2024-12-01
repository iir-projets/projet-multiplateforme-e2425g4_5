package ma.plantes.backend.service;

import ma.plantes.backend.entities.Article;
import ma.plantes.backend.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // Ajouter un nouvel article
    public Article ajouterArticle(Article article) {
        article.getImages().forEach((articleImage -> articleImage.setArticle(article)));
        return articleRepository.save(article);
    }

    // Modifier un article existant
    public Article modifierArticle(Long id, Article article) {
        // Vérifier si l'article existe
        Optional<Article> existingArticle = articleRepository.findById(id);
        if (existingArticle.isPresent()) {
            article.setId(id);  // Assurer que l'ID de l'article modifié est correct
            return articleRepository.save(article);
        }
        return null; // Si l'article n'existe pas, retourner null
    }

    // Supprimer un article
    public boolean supprimerArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            articleRepository.deleteById(id);
            return true; // Article supprimé avec succès
        }
        return false; // L'article n'existe pas
    }

    // Afficher tous les articles
    public List<Article> afficherTousLesArticles() {
        return articleRepository.findAll();
    }

    // Afficher un article par ID
    public Optional<Article> afficherArticleParId(Long id) {
        return articleRepository.findById(id);
    }
}
