package ma.plantes.backend.service;

import ma.plantes.backend.entities.Article;
import ma.plantes.backend.repositories.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;



    @InjectMocks
    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testModifierArticle() {
        Article article = new Article();
        article.setId(1L);
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        Article result = articleService.modifierArticle(1L, article);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(articleRepository, times(1)).findById(1L);
        verify(articleRepository, times(1)).save(any(Article.class));
    }


    @Test
    void testAfficherTousLesArticles() {
        articleService.afficherTousLesArticles();
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    void testAfficherArticleParId() {
        articleService.afficherArticleParId(1L);
        verify(articleRepository, times(1)).findById(1L);
    }
}