package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.service.FavorisService;
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

class FavorisControllerTest {

    @Mock
    private FavorisService favorisService;

    @InjectMocks
    private FavorisController favorisController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterFavoris() {
        Long clientId = 1L;
        Long planteId = 1L;

        ResponseEntity<String> response = favorisController.ajouterFavoris(clientId, planteId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Favori ajouté avec succès !", response.getBody());
        verify(favorisService, times(1)).ajouterFavoris(clientId, planteId);
    }


    @Test
    void testAfficherTousLesFavoris() {
        when(favorisService.getAllFavoris()).thenReturn(Collections.emptyList());

        List<Favoris> response = favorisController.afficherTousLesFavoris();

        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(favorisService, times(1)).getAllFavoris();
    }
}