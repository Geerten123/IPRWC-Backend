package com.example.iprwcbackendcode.repository;

import com.example.iprwcbackendcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    User findUserById(UUID id);
}
