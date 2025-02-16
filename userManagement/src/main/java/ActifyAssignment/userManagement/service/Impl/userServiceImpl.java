package ActifyAssignment.userManagement.service.Impl;

import ActifyAssignment.userManagement.entity.Role;
import ActifyAssignment.userManagement.entity.UserEntity;
import ActifyAssignment.userManagement.repository.RoleRepository;
import ActifyAssignment.userManagement.repository.UserRepository;
import ActifyAssignment.userManagement.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;




    @Override
    public UserEntity signUpUser(UserEntity user){


        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email is already Exist");
        }
        if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new RuntimeException("Password and Confirmed Password doesn't match");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));



        UserEntity createdUser =userRepository.save(user);

        return createdUser;
    }
    @Override
    public List<UserEntity>getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id){
        Optional<UserEntity> userEntityOptional=userRepository.findById(id);
        return userEntityOptional.orElse(null);
    }

    @Override
    @Transactional
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            existingUser.setConfirmPassword(passwordEncoder.encode(updatedUser.getConfirmPassword()));
        }

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }


    @Override
    @Transactional
    public UserEntity assignRoleToUser(Long userId, Long roleId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleId));

        user.getRoles().add(role);
        return userRepository.save(user);
    }

}
