package ActifyAssignment.userManagement.controller;


import ActifyAssignment.userManagement.entity.UserEntity;
import ActifyAssignment.userManagement.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/User")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity>signUpUser(@RequestBody UserEntity user){
        UserEntity createdUser=userService.signUpUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }




}
