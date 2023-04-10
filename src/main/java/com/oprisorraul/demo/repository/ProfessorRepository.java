package com.oprisorraul.demo.repository;

import com.oprisorraul.demo.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Optional<Object> findByEmail(String email);
}