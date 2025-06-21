package com.project.auth.modules.jwt.controller;

import com.project.auth.modules.jwt.models.LoginJwtRequest;
import com.project.auth.modules.jwt.models.LoginJwtResponse;
import com.project.auth.modules.jwt.models.RefreshTokenRequest;
import com.project.auth.modules.jwt.service.AuthJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth-jwt")
@RequiredArgsConstructor
public class AuthJwtController {
    private final AuthJwtService authJwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginJwtResponse> login(@RequestBody LoginJwtRequest loginRequest) {
        LoginJwtResponse response = authJwtService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginJwtResponse> refresh(@RequestBody RefreshTokenRequest request) {
        LoginJwtResponse response = authJwtService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }
}
