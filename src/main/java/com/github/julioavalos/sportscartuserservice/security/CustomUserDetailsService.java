package com.github.julioavalos.sportscartuserservice.security;
import com.github.julioavalos.sportscartuserservice.model.User;
import com.github.julioavalos.sportscartuserservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;      // (Only needed if you plan to change passwords here)

    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Fetch your entity (change findByEmail if that's your repo method):
        User user = userRepository
            .findByUsername(username)     // <-- make sure this method exists
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // 2. Map your entity to Spring Security’s UserDetails:
        return new org.springframework.security.core.userdetails.User(
            user.getFirstName(),          // principal (username)
            user.getPasswordHash(),      // credential
            Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")   // or empty list
            )
        );
    }
}
