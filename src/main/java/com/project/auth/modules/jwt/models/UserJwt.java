package com.project.auth.modules.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "userJwt", timeToLive = 3600)
public class UserJwt implements Serializable {
    @Id
    private UUID id;

    @Indexed
    private String username;

    private String email;
    private String password;
    private String role;
}
