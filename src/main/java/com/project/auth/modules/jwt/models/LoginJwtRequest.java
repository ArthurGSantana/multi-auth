package com.project.auth.modules.jwt.models;

import lombok.Data;

@Data
public class LoginJwtRequest {
    private String username;
    private String password;
}
