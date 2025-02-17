package ActifyAssignment.userManagement.service.Impl;

import ActifyAssignment.userManagement.entity.Role;
import ActifyAssignment.userManagement.repository.RoleRepository;
import ActifyAssignment.userManagement.repository.UserRepository;
import ActifyAssignment.userManagement.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role addRole(Role role){
        Optional<Role> existingRole = roleRepository.findByName(role.getName());
        if (existingRole.isPresent()) {
            throw new RuntimeException("Role '" + role.getName() + "' already exists!");
        }
        return roleRepository.save(role);
    }


    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Long id, Role updatedRole) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));

        existingRole.setName(updatedRole.getName());
        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
        roleRepository.delete(role);
    }


}
