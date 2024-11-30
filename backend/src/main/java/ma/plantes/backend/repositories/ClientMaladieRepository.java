package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.ClientAllergie;
import ma.plantes.backend.entities.ClientMaladie;
import ma.plantes.backend.entities.Maladie;
import ma.plantes.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientMaladieRepository extends JpaRepository<ClientMaladie, Long> {
    List<ClientMaladie> findClientMaladiesByUser(User user);

    Optional<ClientMaladie> findClientMaladieByUserAndMaladie(User user, Maladie maladie);
}
