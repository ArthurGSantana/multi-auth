package com.project.auth.modules.jwt.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginJwtResponse {
    private String username;
    private String accessToken;
    private String refreshToken;
}
