package ma.plantes.backend.service;

import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.repositories.FavorisRepository;
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

class FavorisServiceTest {

    @Mock
    private FavorisRepository favorisRepository;

    @InjectMocks
    private FavorisService favorisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllFavoris() {
        when(favorisRepository.findAll()).thenReturn(Collections.emptyList());

        List<Favoris> result = favorisService.getAllFavoris();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(favorisRepository, times(1)).findAll();
    }
}