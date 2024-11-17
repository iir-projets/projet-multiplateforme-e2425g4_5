package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanteRepository extends JpaRepository<Plante, Long> {

    List<Plante> findByNom(String nom);

    List<Plante> findByProprieteNom(String proprieteNom);




}
