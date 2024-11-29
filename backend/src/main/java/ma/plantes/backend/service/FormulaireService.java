package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Allergie;
import ma.plantes.backend.repositories.ClientAllergieRepository;
import ma.plantes.backend.repositories.ClientMaladieRepository;
import ma.plantes.backend.repositories.ClientMedicamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FormulaireService {

    private ClientAllergieRepository clientAllergieRepository;
    private ClientMaladieRepository clientMaladieRepository;
    private ClientMedicamentRepository clientMedicamentRepository;


}
