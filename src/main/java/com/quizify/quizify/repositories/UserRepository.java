package com.quizify.quizify.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizify.quizify.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}