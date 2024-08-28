package com.example.curd.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curd.dao.UserRepository;
import com.example.curd.entity.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User createUser(User user) {
        if (isEmailExists(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update logic
    public User updateUser(Long id, User updatedUser) {
        return getUserById(id).map(user -> {
            Optional<User> existingUserWithEmail = userRepository.findByEmail(updatedUser.getEmail());
            if (existingUserWithEmail.isPresent() && !existingUserWithEmail.get().getId().equals(user.getId())) {
                throw new IllegalArgumentException("Email is already in use by another user.");
            }
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    // // resultUser.ifPresent(user -> {
    // // user.setName(updatedUserDetails.getName());
    // // user.setEmail(updatedUserDetails.getEmail());
    // // userRepository.save(user);

    // // });
    // // if(resultUser.isPresent()) return resultUser.get();
    // // return null;
    // }

    // Delete Logic
    // Optional<User> resultUser = getUserById(id);
    // if (resultUser.isPresent()) {
    // userRepository.deleteById(id);
    // return resultUser.get();
    // }
    // return null;
    public User deleteUser(Long id) {
        return getUserById(id).map(user -> {
            userRepository.deleteById(id);
            return user;
        }).orElseThrow(() -> new IllegalArgumentException("User does'nt exit with the ID: " + id));

    }
}
