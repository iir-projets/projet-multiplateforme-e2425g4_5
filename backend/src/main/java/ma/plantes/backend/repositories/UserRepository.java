package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Role;
import ma.plantes.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   Optional<User> findByUsername(String username);
   List<User> findUsersByRole(Role role);
}
