package ma.plantes.backend.service;

import ma.plantes.backend.dto.PlanteDto;
import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.repositories.PlanteRepository;
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

class PlanteServiceTest {

    @Mock
    private PlanteRepository planteRepository;

    @InjectMocks
    private PlanteService planteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPlante() {
        when(planteRepository.findAll()).thenReturn(Collections.emptyList());

        List<Plante> result = planteService.afficherToutesLesPlantes();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(planteRepository, times(1)).findAll();
    }
}