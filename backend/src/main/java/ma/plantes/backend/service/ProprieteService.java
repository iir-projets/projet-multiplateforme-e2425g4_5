package ma.plantes.backend.service;

import ma.plantes.backend.entities.Propriete;
import ma.plantes.backend.repositories.ProprieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProprieteService {

    private final ProprieteRepository proprieteRepository;

    @Autowired
    public ProprieteService(ProprieteRepository proprieteRepository) {
        this.proprieteRepository = proprieteRepository;
    }

    public List<Propriete> getAllProprietes() {
        return proprieteRepository.findAll();
    }

    public Optional<Propriete> getProprieteById(Long id) {
        return proprieteRepository.findById(id);
    }

    public List<Propriete> getProprietesByNom(String nom) {
        return proprieteRepository.findByNom(nom);
    }

    public List<Propriete> getProprietesByPlanteId(Long planteId) {
        return proprieteRepository.findByPlantes_Id(planteId);
    }

    public Propriete createPropriete(Propriete propriete) {
        return proprieteRepository.save(propriete);
    }

    public Propriete updatePropriete(Long id, Propriete propriete) {
        if (proprieteRepository.existsById(id)) {
            propriete.setId(id);
            return proprieteRepository.save(propriete);
        }
        return null;
    }

    public void deletePropriete(Long id) {
        if (proprieteRepository.existsById(id)) {
            proprieteRepository.deleteById(id);
        }
    }
}
