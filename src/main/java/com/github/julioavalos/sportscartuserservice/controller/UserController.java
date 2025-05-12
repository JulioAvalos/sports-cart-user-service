package com.github.julioavalos.sportscartuserservice.controller;

import com.github.julioavalos.sportscartuserservice.dto.LoginDto;
import com.github.julioavalos.sportscartuserservice.dto.RegisterDto;
import com.github.julioavalos.sportscartuserservice.dto.UpdateDto;
import com.github.julioavalos.sportscartuserservice.model.User;
import com.github.julioavalos.sportscartuserservice.security.JwtProvider;
import com.github.julioavalos.sportscartuserservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService svc;
    private final JwtProvider jwt;

    public UserController(UserService svc, JwtProvider jwt) {
        this.svc = svc;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        // validate email format and age ≥ 18 here
        User user = dto.toUser();
        User saved = svc.register(user);
        String token = jwt.generateToken(saved.getEmail());
        return ResponseEntity.ok(Map.of("token", token, "user", saved));
    }

   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody LoginDto dto) {
       User user = svc.login(dto.getEmail(), dto.getPassword());
       String token = jwt.generateToken(user.getEmail());
       return ResponseEntity.ok(Map.of("token", token, "user", user));
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
