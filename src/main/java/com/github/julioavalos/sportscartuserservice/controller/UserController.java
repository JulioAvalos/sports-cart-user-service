package com.github.julioavalos.sportscartuserservice.controller;

import com.github.julioavalos.sportscartuserservice.dto.RegisterDto;
import com.github.julioavalos.sportscartuserservice.dto.UpdateDto;
import com.github.julioavalos.sportscartuserservice.model.User;
import com.github.julioavalos.sportscartuserservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService svc;

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
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        User requestedUser = svc.findById(id);

        String authenticatedEmail = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        if (!requestedUser.getEmail().equals(authenticatedEmail)) {
            return ResponseEntity.status(403).body("Access denied");
        }

        return ResponseEntity.ok(requestedUser);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProfile(@PathVariable Long id, @RequestBody UpdateDto dto) {
        User user = svc.findById(id);

        String authenticatedEmail = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        if (!user.getEmail().equals(authenticatedEmail)) {
            return ResponseEntity.status(403).body("Access denied");
        }

        User updated = svc.partialUpdate(id, dto); // <- Rename the service method if you prefer clarity
        return ResponseEntity.ok(updated);
    }


}
