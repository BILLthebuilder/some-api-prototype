package events.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import events.api.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);

    User findByEmail(String email);

    List<User> findByRoles_Role_RoleNameContainingIgnoreCase(String roleName);

}
