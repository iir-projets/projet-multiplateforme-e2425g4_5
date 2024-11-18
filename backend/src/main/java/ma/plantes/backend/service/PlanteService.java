package ma.plantes.backend.service;

import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.repositories.PlanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanteService {

    private final PlanteRepository planteRepository;

    @Autowired
    public PlanteService(PlanteRepository planteRepository) {
        this.planteRepository = planteRepository;
    }

    public List<Plante> getAllPlantes() {
        return planteRepository.findAll();
    }

    public Optional<Plante> getPlanteById(Long id) {
        return planteRepository.findById(id);
    }

    public List<Plante> getPlantesByNom(String nom) {
        return planteRepository.findByNom(nom);
    }

    public List<Plante> getPlantesByRegion(String region) {
        return planteRepository.findByRegion(region);
    }

    public Plante createPlante(Plante plante) {
        return planteRepository.save(plante);
    }

    public Plante updatePlante(Long id, Plante plante) {
        if (planteRepository.existsById(id)) {
            plante.setId(id);
            return planteRepository.save(plante);
        }
        return null;
    }

    public void deletePlante(Long id) {
        if (planteRepository.existsById(id)) {
            planteRepository.deleteById(id);
        }
    }
}
