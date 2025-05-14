package com.github.julioavalos.sportscartuserservice.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {
    private final Long id;
    private final String firstName;
    private final String lastName;

    public CustomUserDetails(Long id, String username, String password,
                           String firstName, String lastName,
                           Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}