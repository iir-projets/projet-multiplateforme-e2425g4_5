package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.User;
import ma.plantes.backend.service.UserDetailsServiceImp;
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

class ClientControllerTest {

    @Mock
    private UserDetailsServiceImp clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testAfficherTousLesClients() {
        when(clientService.allClients()).thenReturn(Collections.emptyList());

        List<User> response = clientController.allClients().getBody();

        assertNotNull(response);
        assertTrue(response.isEmpty());
        verify(clientService, times(1)).allClients();
    }
}