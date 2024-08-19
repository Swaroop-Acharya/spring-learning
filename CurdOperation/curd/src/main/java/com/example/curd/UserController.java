package com.example.curd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submitUser")
    public String submitUser(@RequestParam("name") String name, @RequestParam("email") String email){
        User user=new User(name,email);
        userRepository.save(user);
        return "Success user added";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        
        userRepository.save(user);
        return "User added";
    }
}
