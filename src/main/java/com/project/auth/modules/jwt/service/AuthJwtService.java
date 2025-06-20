package com.project.auth.modules.jwt.service;

import com.project.auth.modules.jwt.config.JwtConfig;
import com.project.auth.modules.jwt.models.LoginJwtRequest;
import com.project.auth.modules.jwt.models.LoginJwtResponse;
import com.project.auth.modules.jwt.models.UserJwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthJwtService {
    private final UserJwtService userJwtService;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    public LoginJwtResponse login(LoginJwtRequest login) {
        var userJwt = userJwtService.findByUsername(login.getUsername());

        if (!isAuthenticated(login)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return LoginJwtResponse.builder()
                .username(userJwt.getUsername())
                .accessToken(generateAccessToken(userJwt))
                .refreshToken(generateRefreshToken(userJwt))
                .build();
    }

    private Boolean isAuthenticated(LoginJwtRequest login) {
        var userJwt = userJwtService.findByUsername(login.getUsername());
        return userJwt != null && passwordEncoder.matches(login.getPassword(), userJwt.getPassword());
    }

    private String generateAccessToken(UserJwt user) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecret()));

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtConfig.getExpirationAccessToken());

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(now)
                .expiration(expiryDate)
                .claim("userId", user.getId())
                .claim("roles", user.getRole())
                .claim("tokenType", "access") // Identifica explicitamente como access token
                .signWith(key)
                .compact();
    }

    private String generateRefreshToken(UserJwt user) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecret()));

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtConfig.getExpirationRefreshToken());

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(now)
                .expiration(expiryDate)
                .id(UUID.randomUUID().toString()) // Adiciona um ID único para o token
                .claim("userId", user.getId())
                .claim("tokenType", "refresh") // Identifica explicitamente como refresh token
                .signWith(key)
                .compact();
    }
}
