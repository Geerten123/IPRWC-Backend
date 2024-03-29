package com.example.iprwcbackendcode.repository;

import com.example.iprwcbackendcode.model.ERole;
import com.example.iprwcbackendcode.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(ERole name);

}
