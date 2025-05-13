package com.github.julioavalos.sportscartuserservice.controller;

import com.github.julioavalos.sportscartuserservice.dto.RegisterDto;
import com.github.julioavalos.sportscartuserservice.dto.UpdateDto;
import com.github.julioavalos.sportscartuserservice.model.User;
import com.github.julioavalos.sportscartuserservice.service.UserService;
import com.github.julioavalos.sportscartuserservice.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService svc;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        if (svc.existsByEmail(dto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email is already in use.");
        }
        User user = svc.register(dto);
        return ResponseEntity
                .status(201)
                .body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        User user = svc.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody UpdateDto dto) {
        User updated = svc.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}
