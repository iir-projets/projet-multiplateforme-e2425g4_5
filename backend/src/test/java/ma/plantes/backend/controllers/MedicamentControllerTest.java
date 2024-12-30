package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Medicament;
import ma.plantes.backend.service.MedicamentService;
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

class MedicamentControllerTest {

    @Mock
    private MedicamentService medicamentService;

    @InjectMocks
    private MedicamentController medicamentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterMedicament() {
        Medicament medicament = new Medicament();
        when(medicamentService.existsByNom(anyString())).thenReturn(false);

        ResponseEntity<String> response = medicamentController.AjouterMedicament(medicament);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Médicament ajouté avec succès !", response.getBody());
        verify(medicamentService, times(1)).ajouterMedicament(medicament);
    }


    @Test
    void testAfficherTousLesMedicaments() {
        when(medicamentService.getAllMedicament()).thenReturn(Collections.emptyList());

        List<Medicament> response = medicamentController.AfficherMedicament();

        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(medicamentService, times(1)).getAllMedicament();
    }
}