package ActifyAssignment.userManagement.repository;

import ActifyAssignment.userManagement.entity.Role;
import ActifyAssignment.userManagement.model.Roletype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role>findByName(Roletype name);
    Optional<Role> findByName(String name);

}
