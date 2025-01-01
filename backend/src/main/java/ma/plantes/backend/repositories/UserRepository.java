package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.Role;
import ma.plantes.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   Optional<User> findByUsername(String username);
   @Query("SELECT u FROM User u WHERE u.role = :role")
   List<User> findUsersByRole(@Param("role") Role role);

   long countByRole(Role role);
}
