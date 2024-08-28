package com.example.curd.controller;

import java.util.List;

import jakarta.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.curd.dto.ApiResponse;
import com.example.curd.entity.User;
import com.example.curd.service.UserService;

@RestController
@RequestMapping("/api/users")

public class ApiUserController {
    @Autowired
    private UserService userService;

    // Insert the user into the table
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            ApiResponse response = new ApiResponse("success", "User created successfully", createdUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ApiResponse response = new ApiResponse("error", e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Retreive all the users from the table
    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("sucess", "Retrieved all the users", users));
    }

    // Update the user
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUse(@PathVariable Long id, @Valid @RequestBody User updatedUserDetails) {
        try {
            User resultUser = userService.updateUser(id, updatedUserDetails);
            ApiResponse response = new ApiResponse("success", "User updated sucessfully", resultUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ApiResponse response = new ApiResponse("error", e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    // Delete the User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            User deletedUser = userService.deleteUser(id);
            ApiResponse response = new ApiResponse("success", "User deleted successfully", deletedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("error", e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
