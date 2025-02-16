package ActifyAssignment.userManagement.service.Impl;

import ActifyAssignment.userManagement.entity.UserEntity;
import ActifyAssignment.userManagement.repository.UserRepository;
import ActifyAssignment.userManagement.service.UserService;
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
    private BCryptPasswordEncoder passwordEncoder;




    @Override
    public UserEntity signUpUser(UserEntity user){

        UserEntity createdUser =userRepository.save(user);

        return createdUser;
    }





}
