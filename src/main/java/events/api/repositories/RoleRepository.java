package events.api.repositories;

import events.api.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID>{
    Role findByRoleName(String roleName);
}
