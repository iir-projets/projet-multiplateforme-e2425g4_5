package ma.plantes.backend.service;

import ma.plantes.backend.entities.Medicament;
import ma.plantes.backend.repositories.MedicamentRepository;
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

class MedicamentServiceTest {

    @Mock
    private MedicamentRepository medicamentRepository;

    @InjectMocks
    private MedicamentService medicamentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterMedicament() {
        Medicament medicament = new Medicament();

        medicamentService.ajouterMedicament(medicament);

        verify(medicamentRepository, times(1)).save(medicament);
    }

    @Test
    void testSupprimerMedicament() {
        when(medicamentRepository.existsById(1L)).thenReturn(true);

        boolean result = medicamentService.supprimerMedicament(1L);

        assertTrue(result);
        verify(medicamentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllMedicament() {
        when(medicamentRepository.findAll()).thenReturn(Collections.emptyList());

        List<Medicament> result = medicamentService.getAllMedicament();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(medicamentRepository, times(1)).findAll();
    }
}