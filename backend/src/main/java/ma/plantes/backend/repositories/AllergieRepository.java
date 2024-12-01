package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Allergie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergieRepository extends JpaRepository<Allergie,Long> {
    boolean existsByNom(String nom);
}
