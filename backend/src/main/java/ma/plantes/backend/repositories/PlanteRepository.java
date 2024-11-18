package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanteRepository extends JpaRepository<Plante, Long> {


    List<Plante> findByNom(String nom);

    List<Plante> findByRegion(String region);
}
