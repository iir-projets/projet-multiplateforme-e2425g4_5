package ma.plantes.backend.service;

import ma.plantes.backend.dto.CommentaireDTO;
import ma.plantes.backend.entities.Article;
import ma.plantes.backend.entities.Commentaire;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.repositories.ArticleRepository;
import ma.plantes.backend.repositories.CommentaireRepository;
import ma.plantes.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentaireServiceTest {

    @Mock
    private CommentaireRepository commentaireRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentaireService commentaireService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterCommentaire() {
        CommentaireDTO commentaireDTO = new CommentaireDTO();
        commentaireDTO.setContenu("Test");
        commentaireDTO.setArticleId(1L);
        commentaireDTO.setUserId(1L);

        Article article = new Article();
        User user = new User();
        Commentaire commentaire = new Commentaire();

        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(commentaireRepository.save(any(Commentaire.class))).thenReturn(commentaire);

        Commentaire result = commentaireService.ajouterCommentaire(commentaireDTO);

        assertNotNull(result);
        verify(commentaireRepository, times(1)).save(any(Commentaire.class));
    }

    @Test
    void testSupprimerCommentaire() {
        when(commentaireRepository.existsById(1L)).thenReturn(true);

        boolean result = commentaireService.supprimerCommentaire(1L);

        assertTrue(result);
        verify(commentaireRepository, times(1)).deleteById(1L);
    }

    @Test
    void testTrouverCommentairesParArticle() {
        when(commentaireRepository.findByArticleId(1L)).thenReturn(Collections.emptyList());

        List<Commentaire> result = commentaireService.trouverCommentairesParArticle(1L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(commentaireRepository, times(1)).findByArticleId(1L);
    }

    @Test
    void testTrouverCommentairesParUtilisateur() {
        when(commentaireRepository.findByUtilisateurId(1L)).thenReturn(Collections.emptyList());

        List<Commentaire> result = commentaireService.trouverCommentairesParUtilisateur(1L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(commentaireRepository, times(1)).findByUtilisateurId(1L);
    }
}