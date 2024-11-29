package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.ClientAllergie;
import ma.plantes.backend.entities.ClientMaladie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientMaladieRepository extends JpaRepository<ClientMaladie, Long> {
    
}
