package com.sdc.study.security.service.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(String role);
}
