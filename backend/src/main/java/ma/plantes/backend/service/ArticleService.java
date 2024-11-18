package ma.plantes.backend.service;

import ma.plantes.backend.entities.Article;
import ma.plantes.backend.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public List<Article> getArticlesByPlanteId(Long planteId) {
        return articleRepository.findByPlanteId(planteId);
    }

    public List<Article> getArticlesByTitre(String titre) {
        return articleRepository.findByTitre(titre);
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article article) {
        if (articleRepository.existsById(id)) {
            article.setId(id);
            return articleRepository.save(article);
        }
        return null;
    }

    public void deleteArticle(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        }
    }
}
