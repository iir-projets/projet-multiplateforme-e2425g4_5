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
public interface FavorisRepository extends JpaRepository<Favoris,FavorisId> {

    boolean existsById(FavorisId favorisId);
    //boolean deleteById(FavorisId favorisId);

    // Custom query to fetch favoris by clientId
    List<Favoris> findAllByUserId(Long clientId);


    @Query(value = "SELECT p.id AS plante_id, p.nom_plante, COUNT(p.id) AS total " +
            "FROM favoris f " +
            "JOIN plante p ON f.plante_id = p.id " +
            "GROUP BY f.plante_id, p.nom_plante " +
            "ORDER BY total DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5PlantesByFavoris();

    @Query("SELECT f.plante FROM Favoris f WHERE f.user.id = :clientId")
    List<Plante> findPlantesByClientId(Long clientId);
}
