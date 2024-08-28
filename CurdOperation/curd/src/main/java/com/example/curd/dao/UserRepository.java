package com.example.curd.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.curd.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}