package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientMedicamentRepository extends JpaRepository<ClientMedicament, Long> {
    List<ClientMedicament> findClientMedicamentsByUser(User user);

    Optional<ClientMedicament> findClientMedicamentByUserAndMedicament(User user, Medicament medicament);
}
