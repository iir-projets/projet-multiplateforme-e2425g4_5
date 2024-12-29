package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.entities.FavorisId;
import ma.plantes.backend.entities.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavorisRepository extends JpaRepository<Favoris,Long> {

    boolean existsById(FavorisId favorisId);
    boolean deleteById(FavorisId favorisId);
    List<Plante> findAllByUserId(Long clientId);

    Optional<Favoris> findById(FavorisId favorisId);
}
