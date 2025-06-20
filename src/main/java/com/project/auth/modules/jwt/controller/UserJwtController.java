package com.project.auth.modules.jwt.controller;

import com.project.auth.modules.jwt.models.UserJwt;
import com.project.auth.modules.jwt.service.UserJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-jwt")
@RequiredArgsConstructor
public class UserJwtController {
    private final UserJwtService userJwtService;

    @PostMapping
    public ResponseEntity<String> createUserJwt(@RequestBody UserJwt user) {
        var userJwt = userJwtService.createUserJwt(user);
        return ResponseEntity.ok("User JWT created with ID: " + userJwt.getId());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserJwt> getUserJwt(@PathVariable String username) {
        UserJwt userJwt = userJwtService.findByUsername(username);
        if (userJwt == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userJwt);
    }
}
