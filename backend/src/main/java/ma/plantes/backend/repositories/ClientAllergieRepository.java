package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientAllergieRepository extends JpaRepository<ClientAllergie, Long> {
    List<ClientAllergie> findClientAllergiesByUser(User user);

    Optional<ClientAllergie> findClientAllergieByUserAndAllergie(User user, Allergie allergie);
}
