package com.project.auth.modules.jwt.repository;

import com.project.auth.modules.jwt.models.UserJwt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJwtRepository extends CrudRepository<UserJwt, UUID> {
    Optional<UserJwt> findByUsername(String username);
    boolean existsByUsername(String username);
}
