package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.entities.FavorisId;
import ma.plantes.backend.entities.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavorisRepository extends JpaRepository<Favoris,Long> {
    List<Favoris> findByUser_Id(Long clientId);


    boolean existsById(FavorisId favorisId); // Check if the favorite exists
    void deleteById(FavorisId favorisId);

    List<Plante> findAllByUserId(Long clientId);

    Optional<Favoris> findById(FavorisId favorisId);

    @Query(value = "SELECT f.plante_id, COUNT(f.plante_id) AS total " +
            "FROM favoris f " +
            "GROUP BY f.plante_id " +
            "ORDER BY total DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5PlantesByFavoris();
}
