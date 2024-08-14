package com.example.mybookapp.controllers;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybookapp.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Long,User> userDatabase = new HashMap<>();
    private Long userId=1L;


    @PostMapping
    public User createUser(@RequestBody User user){
        user.setId(userId++);
        userDatabase.put(user.getId(),user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        if(userDatabase.containsKey(id)){
            user.setId(id);
            userDatabase.put(id,user);
            return user;
        }else return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userDatabase.containsKey(id)) {
            userDatabase.remove(id);
            return "User deleted";
        } else {
            return "User not found";
        }
    }


    
}
