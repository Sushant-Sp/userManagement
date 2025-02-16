package ActifyAssignment.userManagement.controller;


import ActifyAssignment.userManagement.entity.UserEntity;
import ActifyAssignment.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/get")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> userEntities=userService.getAllUsers();
        return ResponseEntity.ok(userEntities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        UserEntity user=userService.getUserById(id);
        if(user !=null){
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/assign-role/{roleId}")
    public ResponseEntity<UserEntity> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        UserEntity updatedUser = userService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok(updatedUser);
    }



}
