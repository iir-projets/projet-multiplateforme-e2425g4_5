package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Maladie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaladieRepository extends JpaRepository<Maladie,Long> {
    boolean existsByNom(String nom);

    @Query("SELECT COUNT(m) FROM Maladie m")
        long countMaladies();


}