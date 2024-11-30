package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.entities.FavorisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorisRepository extends JpaRepository<Favoris,Long> {

    boolean existsById(FavorisId favorisId);
    boolean deleteById(FavorisId favorisId);
}
