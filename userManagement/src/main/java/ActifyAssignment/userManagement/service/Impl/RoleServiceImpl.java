package ActifyAssignment.userManagement.service.Impl;

import ActifyAssignment.userManagement.entity.Role;
import ActifyAssignment.userManagement.repository.RoleRepository;
import ActifyAssignment.userManagement.repository.UserRepository;
import ActifyAssignment.userManagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Role createdRole=roleRepository.save(role);
        return createdRole;
    }


}
