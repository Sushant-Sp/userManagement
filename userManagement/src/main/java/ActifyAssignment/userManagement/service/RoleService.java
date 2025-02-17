package ActifyAssignment.userManagement.service;

import ActifyAssignment.userManagement.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Role addRole(Role role);
    Role getRoleById(Long id);
    List<Role> getAllRoles();
    Role updateRole(Long id, Role updatedRole);
    void deleteRole(Long id);

}
