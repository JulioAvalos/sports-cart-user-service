package com.github.julioavalos.sportscartuserservice.controller;

import com.github.julioavalos.sportscartuserservice.dto.JwtResponse;
import com.github.julioavalos.sportscartuserservice.dto.LoginDto;
import com.github.julioavalos.sportscartuserservice.util.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto) {

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtUtils.generateJwtToken((UserDetails) auth.getPrincipal());

        long expiresInSeconds = jwtUtils.getJwtExpirationMs() / 1000;

        JwtResponse response = JwtResponse.builder()
            .accessToken(token)
            .tokenType("Bearer")
            .expiresIn(expiresInSeconds)
            .build();

        return ResponseEntity.ok(response);
    }
}

