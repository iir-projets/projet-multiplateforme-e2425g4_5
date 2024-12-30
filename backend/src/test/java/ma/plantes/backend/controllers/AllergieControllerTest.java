package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Allergie;
import ma.plantes.backend.service.AllergieService;
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

class AllergieControllerTest {

    @Mock
    private AllergieService allergieService;

    @InjectMocks
    private AllergieController allergieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterAllergie() {
        Allergie allergie = new Allergie();
        allergie.setNom("Pollen");
        when(allergieService.existsByName("Pollen")).thenReturn(false);

        ResponseEntity<String> response = allergieController.AjouterAllergie(allergie);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Allergie ajoutée avec succès !", response.getBody());
        verify(allergieService, times(1)).ajouterAllergie(allergie);
    }

    @Test
    void testSupprimerAllergie() {
        when(allergieService.exsistById(1L)).thenReturn(true);

        ResponseEntity<String> response = allergieController.SupprimerAllergie(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Allergie supprimée avec succès !", response.getBody());
        verify(allergieService, times(1)).supprimerAllergie(1L);
    }

    @Test
    void testAfficherAllergie() {
        when(allergieService.getAllAllergie()).thenReturn(Collections.emptyList());

        List<Allergie> response = allergieController.AfficherAllergie();

        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(allergieService, times(1)).getAllAllergie();
    }
}