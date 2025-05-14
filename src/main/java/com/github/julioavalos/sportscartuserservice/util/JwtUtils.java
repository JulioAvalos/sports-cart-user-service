package com.github.julioavalos.sportscartuserservice.util;

import com.github.julioavalos.sportscartuserservice.dto.CustomUserDetails;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Getter
    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    public String generateJwtToken(UserDetails userDetails) {
        if (!(userDetails instanceof CustomUserDetails customUser)) {
            throw new IllegalArgumentException("UserDetails must be an instance of CustomUserDetails");
        }

        return Jwts.builder()
                .setSubject(customUser.getUsername())
                .claim("id", customUser.getId())
                .claim("firstName", customUser.getFirstName())
                .claim("lastName", customUser.getLastName())
                .claim("role", "ROLE_USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

}
