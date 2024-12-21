package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.PlantePropriete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanteProprieteRepository extends JpaRepository<PlantePropriete,Long> {

    List<PlantePropriete> findPlanteProprietesByProprieteId(Long id);
}
