package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
}
