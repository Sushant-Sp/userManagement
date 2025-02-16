package ActifyAssignment.userManagement.service;

import ActifyAssignment.userManagement.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserEntity signUpUser(UserEntity user);
    List<UserEntity>getAllUsers();
    UserEntity getUserById(Long id);
    UserEntity assignRoleToUser(Long userId, Long roleId);
    UserEntity updateUser(Long id, UserEntity updatedUser);
    void deleteUser(Long id);




}
