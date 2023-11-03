package com.nutech.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + 43200000);

        return Jwts.builder()
                .header().add("typ", "JWT").and()
                .claim("email", email)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(Jwts.SIG.HS256.key().build())
                .compact();
    }

    public String getEmailFromJwt(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Jwts.SIG.HS256.key().build())
                .build()
                .parseUnsecuredClaims(token)
                .getPayload();
        return (String) claims.get("email");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Jwts.SIG.HS256.key().build())
                    .build()
                    .parseUnsecuredClaims(token);
            return true;
        } catch (Exception exception) {
            throw new AuthenticationCredentialsNotFoundException(
                    "Token tidak tidak valid atau kadaluwarsa",
                    exception.fillInStackTrace()
            );
        }

    }

}
