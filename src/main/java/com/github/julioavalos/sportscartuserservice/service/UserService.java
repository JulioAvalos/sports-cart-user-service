package com.github.julioavalos.sportscartuserservice.service;

import com.github.julioavalos.sportscartuserservice.dto.RegisterDto;
import com.github.julioavalos.sportscartuserservice.dto.UpdateDto;
import com.github.julioavalos.sportscartuserservice.model.User;
import com.github.julioavalos.sportscartuserservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public User register(User user) {
        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        return repo.save(user);
    }

    public User login(String email, String password) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!encoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    public User register(RegisterDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPasswordHash(encoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthdate(dto.getBirthdate());
        user.setAddress(dto.getAddress());
        return repo.save(user);
    }

    public User findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User update(Long id, UpdateDto user) {
        User existingUser = findById(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setBirthdate(user.getBirthdate());
        existingUser.setAddress(user.getAddress());
        return repo.save(existingUser);
    }
}
