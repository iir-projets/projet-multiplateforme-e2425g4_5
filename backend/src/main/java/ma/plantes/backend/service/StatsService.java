package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.repositories.AllergieRepository;
import ma.plantes.backend.repositories.MaladieRepository;
import ma.plantes.backend.repositories.MedicamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class StatsService {

    private final AllergieRepository allergieRepository;
    private final MaladieRepository maladieRepository;
    private final MedicamentRepository medicamentRepository;


    public List<Map<String, Object>> getCountByCategories() {
        List<Map<String, Object>> results = new ArrayList<>();

        // Récupérer les totaux pour chaque catégorie
        long maladieCount = maladieRepository.countMaladies();
        long allergieCount = allergieRepository.countAllergies();
        long medicamentCount = medicamentRepository.countMedicaments();

        // Ajouter les données dans un format structuré
        results.add(Map.of("category", "Maladies", "total", maladieCount));
        results.add(Map.of("category", "Allergies", "total", allergieCount));
        results.add(Map.of("category", "Médicaments", "total", medicamentCount));

        return results;
    }
}
