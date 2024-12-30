package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Maladie;
import ma.plantes.backend.service.MaladieService;
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

class MaladieControllerTest {

    @Mock
    private MaladieService maladieService;

    @InjectMocks
    private MaladieController maladieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterMaladie() {
        Maladie maladie = new Maladie();
        when(maladieService.existsByNom(anyString())).thenReturn(false);

        ResponseEntity<String> response = maladieController.AjouterMaladie(maladie);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Maladie ajoutée avec succès !", response.getBody());
        verify(maladieService, times(1)).ajouterMaladie(maladie);
    }

    @Test
    void testSupprimerMaladie() {
        when(maladieService.existsById(1L)).thenReturn(true);

        ResponseEntity<String> response = maladieController.SupprimerMaladie(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Maladie supprimée avec succès", response.getBody());
        verify(maladieService, times(1)).supprimerMaladie(1L);
    }

    @Test
    void testAfficherMaladie() {
        when(maladieService.getAllMaladie()).thenReturn(Collections.emptyList());

        List<Maladie> response = maladieController.AfficherMaladie();

        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(maladieService, times(1)).getAllMaladie();
    }
}