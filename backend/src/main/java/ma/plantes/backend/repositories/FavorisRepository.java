package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Favoris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorisRepository extends JpaRepository<Favoris,Long> {
}
