package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Plante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanteRepository extends JpaRepository<Plante, Long> {

    // Rechercher une plante par son nom
    Optional<Plante> findByNom(String nom);

    // Rechercher des plantes par région
    List<Plante> findByRegion(String region);


    // Rechercher toutes les plantes ayant une propriété spécifique (utilisant la relation ManyToMany)
    List<Plante> findByProprietes_Id(Long proprieteId);
}
