package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.PlanteDto;
import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.entities.PlantePropriete;
import ma.plantes.backend.entities.PlanteProprieteId;
import ma.plantes.backend.entities.Propriete;
import ma.plantes.backend.repositories.PlanteProprieteRepository;
import ma.plantes.backend.repositories.PlanteRepository;
import ma.plantes.backend.repositories.ProprieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanteService {

    private final PlanteRepository planteRepository;
    private final PlanteProprieteRepository planteProprieteRepository;
    private final ProprieteRepository proprieteRepository;



    // Ajouter une nouvelle plante
    public Plante ajouterPlante(PlanteDto plante) {

        Plante newPlante = new Plante();
        newPlante.setRegion(plante.getRegion());
        newPlante.setNom(plante.getNom());
        if (plante.getImage() != null) newPlante.setImage(plante.getImage());
        if (plante.getPrecaution() != null) newPlante.setPrecaution(plante.getPrecaution());
        if (plante.getInteraction() != null) newPlante.setInteraction(plante.getInteraction());
        if (plante.getDescription() != null) newPlante.setDescription(plante.getDescription());

        plante.getProprietes().forEach(proprieteDto -> {
            Propriete propriete = proprieteRepository.findByNom(proprieteDto.getNom());
            newPlante.getProprietes().add(propriete);
        });

        return planteRepository.save(newPlante);
    }

    // Modifier une plante existante
    public Plante modifierPlante(Long id, Plante plante) {
        // Vérifier si la plante existe
        Optional<Plante> existingPlante = planteRepository.findById(id);
        if (existingPlante.isPresent()) {
            plante.setId(id);  // On s'assure que l'ID de la plante modifiée est le bon
            return planteRepository.save(plante);
        }
        return null; // Si la plante n'existe pas, retourner null
    }

    // Supprimer une plante
    public boolean supprimerPlante(Long id) {
        Optional<Plante> plante = planteRepository.findById(id);
        if (plante.isPresent()) {
            planteRepository.deleteById(id);
            return true; // Plante supprimée avec succès
        }
        return false; // La plante n'existe pas
    }

    // Afficher toutes les plantes
    public List<Plante> afficherToutesLesPlantes() {
        return planteRepository.findAll();
    }

    // Afficher une plante par ID
    public Optional<Plante> afficherPlanteParId(Long id) {
        return planteRepository.findById(id);
    }

    // Retourner le total des éléments
    public long getTotalCount() {
        return planteRepository.count();
    }

    public Map<String, Long> getPlantesCountByRegion() {
        List<Object[]> results = planteRepository.countPlantesByRegion();
        Map<String, Long> plantesParRegion = new HashMap<>();

        for (Object[] result : results) {
            String region = (String) result[0];
            Long count = (Long) result[1];
            plantesParRegion.put(region, count);
        }

        return plantesParRegion;
    }

}
