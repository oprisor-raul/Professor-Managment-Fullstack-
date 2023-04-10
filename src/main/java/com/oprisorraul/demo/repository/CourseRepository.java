package com.oprisorraul.demo.repository;

import com.oprisorraul.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByProfessorId(Integer professorId);
}
