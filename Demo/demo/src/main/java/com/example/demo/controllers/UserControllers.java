package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import com.example.demo.modals.*;;

@RestController
@RequestMapping("/users")
public class UserControllers {
    private Map<Long, User> userDatabase = new HashMap<>();
    private Long currentId = 1L;

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(currentId++);
        userDatabase.put(user.getId(), user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        if (userDatabase.containsKey(id)) {
            System.out.println(userDatabase);
            user.setId(id);
            userDatabase.put(id, user);
            return user;
        } else {
            return null; // In a real app, you might throw an exception here
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        if(userDatabase.containsKey(id)){
            userDatabase.remove(id);
            return "User deelted";
        }else return "User not deleted";
    }


}
