package ma.plantes.backend.service;

import ma.plantes.backend.entities.Propriete;
import ma.plantes.backend.repositories.ProprieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProprieteService {

    private final ProprieteRepository proprieteRepository;

    @Autowired
    public ProprieteService(ProprieteRepository proprieteRepository) {
        this.proprieteRepository = proprieteRepository;
    }

    // Ajouter une propriété
    public Propriete ajouterPropriete(Propriete propriete) {
        if (proprieteRepository.existsByNom(propriete.getNom())) {
            return null;  // Si la propriété existe déjà, retourner null
        }
        return proprieteRepository.save(propriete);
    }

    // Supprimer une propriété par son ID
    public boolean supprimerPropriete(Long id) {
        if (proprieteRepository.existsById(id)) {
            proprieteRepository.deleteById(id);
            return true;  // Propriété supprimée avec succès
        }
        return false;  // La propriété n'existe pas
    }
}
