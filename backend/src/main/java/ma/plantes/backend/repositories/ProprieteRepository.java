package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Propriete;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProprieteRepository extends JpaRepository<Propriete, Long> {


    List<Propriete> findByNom(String nom);

    List<Propriete> findByPlantes_Id(Long planteId);
}
