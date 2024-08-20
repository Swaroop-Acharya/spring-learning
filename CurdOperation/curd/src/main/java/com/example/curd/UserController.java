package com.example.curd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //INSERT THE DATA INTO THE TABLE using view
    
    @PostMapping("/submitUser")
    public String submitUser(@RequestParam("name") String name, @RequestParam("email") String email, Model model) {
        User user = new User(name, email);
        userRepository.save(user);

        model.addAttribute("message", "Success user added");
        return "result"; // Return the name of the view (Thymeleaf template)
    }

    @GetMapping("/form")
    public String showForm() {
        return "form"; // Return the name of the view containing the form
    }

    //INSERT THE DATA INTO THE TABLE using JSON
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {     
        userRepository.save(user);
        return "User added";
    }

    //Retreive the data from the table to the view

    @GetMapping("/usersApi")
    public List<User> getUseStringApi() {
        return userRepository.findAll();
    }

    //Retreive the data from the table to the view(Thymeleaf)
    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userList"; // This refers to the 'userList.html' template
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/list"; // Redirect to the list page after deletion
    }
    
}
