package ma.plantes.backend.service;

import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.repositories.PlanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlanteService {

    private final PlanteRepository planteRepository;

    @Autowired
    public PlanteService(PlanteRepository planteRepository) {
        this.planteRepository = planteRepository;
    }

    // Ajouter une nouvelle plante
    public Plante ajouterPlante(Plante plante) {
        return planteRepository.save(plante);
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
}
