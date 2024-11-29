package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.ClientAllergie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAllergieRepository extends JpaRepository<ClientAllergie, Long> {

}
