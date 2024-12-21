package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Propriete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprieteRepository extends JpaRepository<Propriete, Long> {

    // Vérifier si une propriété existe par son nom
    boolean existsByNom(String nom);
    Propriete findByNom(String nom);
}
