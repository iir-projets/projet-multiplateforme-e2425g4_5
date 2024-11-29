package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.ClientAllergie;
import ma.plantes.backend.entities.ClientMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientMedicamentRepository extends JpaRepository<ClientMedicament, Long> {
    
}
