package com.example.curd.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.curd.entity.User;
import com.example.curd.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    @Autowired
    private UserRepository userRepository;

    // Insert the user into the table
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userRepository.save(user);
        return "User added";
    }

    // Retreive all the users from the table
    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
        
    }

    // Update the user
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUserDetails) {
        Optional<User> resultUser = userRepository.findById(id);
        if (resultUser.isPresent()) {
            User user = resultUser.get();
            user.setName(updatedUserDetails.getName());
            user.setEmail(updatedUserDetails.getEmail());
            userRepository.save(user);
            return user;
        }
        return null;
    }

    // @PutMapping("/updateUser/{id}")
    // public User updateUser(@PathVariable Long id, @RequestBody User
    // updatedUserDetails) {
    // return userRepository.findById(id)
    // .map(user -> {
    // user.setName(updatedUserDetails.getName());
    // user.setEmail(updatedUserDetails.getEmail());
    // return userRepository.save(user);
    // })
    // .orElseThrow(() -> new ResourceNotFoundException("User not found with id " +
    // id));
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().body("{\"message\":\"User deleted successfully\",\"userId\":" + id + "}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\":\"User not found\",\"userId\":" + id + "}");
        }
    }
}
