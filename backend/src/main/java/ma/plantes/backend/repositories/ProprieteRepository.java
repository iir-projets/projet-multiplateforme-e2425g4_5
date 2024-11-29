package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Propriete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprieteRepository extends JpaRepository<Propriete, Long> {

    // Vérifier si une propriété existe par son nom
    boolean existsByNom(String nom);
}
