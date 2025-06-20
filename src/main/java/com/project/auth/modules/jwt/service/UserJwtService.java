package com.project.auth.modules.jwt.service;

import com.project.auth.modules.jwt.models.UserJwt;
import com.project.auth.modules.jwt.repository.UserJwtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserJwtService {

    private final UserJwtRepository userJwtRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean existsByUsername(String username) {
        return userJwtRepository.existsByUsername(username);
    }

    public UserJwt findByUsername(String username) {
        return userJwtRepository.findByUsername(username).orElse(null);
    }

    public UserJwt createUserJwt(UserJwt userJwt) {
        if (userJwt.getPassword().isEmpty() || userJwt.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty");
        }

        userJwt.setId(UUID.randomUUID());

        String rawPassword = userJwt.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        userJwt.setPassword(encodedPassword);

        return userJwtRepository.save(userJwt);
    }
}
