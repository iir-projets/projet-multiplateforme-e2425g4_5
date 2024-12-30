package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Article;
import ma.plantes.backend.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleControllerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testModifierArticle() {
        Article article = new Article();
        when(articleService.modifierArticle(anyLong(), any(Article.class))).thenReturn(article);

        ResponseEntity<Article> response = articleController.modifierArticle(1L, article);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(articleService, times(1)).modifierArticle(1L, article);
    }

    @Test
    void testSupprimerArticle() {
        when(articleService.supprimerArticle(1L)).thenReturn(true);

        ResponseEntity<String> response = articleController.supprimerArticle(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Article supprimé avec succès", response.getBody());
        verify(articleService, times(1)).supprimerArticle(1L);
    }

    @Test
    void testAfficherTousLesArticles() {
        when(articleService.afficherTousLesArticles()).thenReturn(Collections.emptyList());

        List<Article> response = articleController.afficherTousLesArticles();

        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(articleService, times(1)).afficherTousLesArticles();
    }

    @Test
    void testAfficherArticleParId() {
        Article article = new Article();
        when(articleService.afficherArticleParId(1L)).thenReturn(Optional.of(article));

        ResponseEntity<Article> response = articleController.afficherArticleParId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(articleService, times(1)).afficherArticleParId(1L);
    }
}