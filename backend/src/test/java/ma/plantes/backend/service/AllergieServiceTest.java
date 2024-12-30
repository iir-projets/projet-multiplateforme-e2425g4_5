package ma.plantes.backend.service;

import ma.plantes.backend.entities.Allergie;
import ma.plantes.backend.repositories.AllergieRepository;
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

class AllergieServiceTest {

    @Mock
    private AllergieRepository allergieRepository;

    @InjectMocks
    private AllergieService allergieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterAllergie() {
        Allergie allergie = new Allergie();

        allergieService.ajouterAllergie(allergie);

        verify(allergieRepository, times(1)).save(allergie);
    }

    @Test
    void testSupprimerAllergie() {
        when(allergieRepository.existsById(1L)).thenReturn(true);

        boolean result = allergieService.supprimerAllergie(1L);

        assertTrue(result);
        verify(allergieRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllAllergie() {
        when(allergieRepository.findAll()).thenReturn(Collections.emptyList());

        List<Allergie> result = allergieService.getAllAllergie();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(allergieRepository, times(1)).findAll();
    }
}