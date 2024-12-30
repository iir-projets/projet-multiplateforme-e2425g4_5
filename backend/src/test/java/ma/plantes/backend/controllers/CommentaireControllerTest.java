package ma.plantes.backend.controllers;

import ma.plantes.backend.dto.CommentaireDTO;
import ma.plantes.backend.entities.Commentaire;
import ma.plantes.backend.service.CommentaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentaireControllerTest {

    @Mock
    private CommentaireService commentaireService;

    @InjectMocks
    private CommentaireController commentaireController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterCommentaire() {
        CommentaireDTO commentaireDTO = new CommentaireDTO();
        Commentaire commentaire = new Commentaire();
        when(commentaireService.ajouterCommentaire(any(CommentaireDTO.class))).thenReturn(commentaire);

        ResponseEntity<Commentaire> response = commentaireController.ajouterCommentaire(commentaireDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(commentaireService, times(1)).ajouterCommentaire(commentaireDTO);
    }


    @Test
    void testAfficherCommentairesParArticle() {
        when(commentaireService.trouverCommentairesParArticle(1L)).thenReturn(Collections.emptyList());

        List<Commentaire> response = commentaireController.getCommentairesParArticle(1L).getBody();

        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(commentaireService, times(1)).trouverCommentairesParArticle(1L);
    }

}