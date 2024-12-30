package ma.plantes.backend.service;

import ma.plantes.backend.entities.Maladie;
import ma.plantes.backend.repositories.MaladieRepository;
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

class MaladieServiceTest {

    @Mock
    private MaladieRepository maladieRepository;

    @InjectMocks
    private MaladieService maladieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterMaladie() {
        Maladie maladie = new Maladie();
        when(maladieRepository.save(any(Maladie.class))).thenReturn(maladie);

        maladieService.ajouterMaladie(maladie);

        verify(maladieRepository, times(1)).save(maladie);
    }

    @Test
    void testSupprimerMaladie() {
        when(maladieRepository.existsById(1L)).thenReturn(true);

        boolean result = maladieService.supprimerMaladie(1L);

        assertTrue(result);
        verify(maladieRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllMaladie() {
        when(maladieRepository.findAll()).thenReturn(Collections.emptyList());

        List<Maladie> result = maladieService.getAllMaladie();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(maladieRepository, times(1)).findAll();
    }
}