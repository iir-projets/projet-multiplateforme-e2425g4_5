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


    boolean existsById(FavorisId favorisId);  // Renvoie un booléen
    void deleteById(FavorisId favorisId);

    List<Favoris> findAllByUserId(Long clientId);




    @Query("SELECT f.plante FROM Favoris f WHERE f.user.id = :clientId")
    List<Plante> findPlantesByClientId(Long clientId);

    @Query(value = "SELECT f.plante_id, COUNT(f.plante_id) AS total " +
            "FROM favoris f " +
            "GROUP BY f.plante_id " +
            "ORDER BY total DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5PlantesByFavoris();
}
