package ma.plantes.backend.controllers;

import ma.plantes.backend.dto.PlanteDto;
import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.service.PlanteService;
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

class PlanteControllerTest {

    @Mock
    private PlanteService planteService;

    @InjectMocks
    private PlanteController planteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAfficherToutesLesPlantes() {
        when(planteService.afficherToutesLesPlantes()).thenReturn(Collections.emptyList());

        List<Plante> response = planteController.afficherToutesLesPlantes();

        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(planteService, times(1)).afficherToutesLesPlantes();
    }
}