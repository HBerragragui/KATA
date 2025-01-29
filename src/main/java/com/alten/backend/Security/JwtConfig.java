package com.alten.backend.Security;

import com.alten.backend.Controllers.AuthController;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtConfig {

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Secret key for signing JWT
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    public static String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(secretKey)
                .compact();
    }

    public String getUserEmail(String token){
        token = token.replace("Bearer ", "").trim();

        try {

            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();
            Claims claims = parser.parseClaimsJws(token)
                    .getBody();

            return (String) claims.get("sub");
        } catch (Exception e) {
            log.error("JWT validation failed: {}", e.getMessage());
            return "false";
        }
    }

    public boolean validateToken(String token) {
        token = token.replace("Bearer ", "").trim();
        try {
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();
            Claims claims = parser.parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            log.error("JWT validation failed: {}", e.getMessage());
            return false;
        }
    }

}