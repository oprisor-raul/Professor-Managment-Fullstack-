package com.oprisorraul.demo.repository;

import com.oprisorraul.demo.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}