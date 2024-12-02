package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanteRepository extends JpaRepository<Plante, Long> {

    // Rechercher une plante par son nom
    Optional<Plante> findByNom(String nom);

    // Rechercher des plantes par région
    List<Plante> findByRegion(String region);


}
