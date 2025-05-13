package com.github.julioavalos.sportscartuserservice.repository;

import com.github.julioavalos.sportscartuserservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}