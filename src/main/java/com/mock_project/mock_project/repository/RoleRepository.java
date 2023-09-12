package com.mock_project.mock_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mock_project.mock_project.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByname(String name);
    
}
