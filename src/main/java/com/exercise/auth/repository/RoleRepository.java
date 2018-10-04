package com.exercise.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
