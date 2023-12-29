package com.example.iprwcbackendcode.repository;

import com.example.iprwcbackendcode.model.ERole;
import com.example.iprwcbackendcode.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRespository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
