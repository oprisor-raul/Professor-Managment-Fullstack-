package com.oprisorraul.demo.repository;

import com.oprisorraul.demo.model.Course;
import com.oprisorraul.demo.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Modifying
    @Query("UPDATE Course c SET c.professor.id = :professorId WHERE c.id = :courseId")
    void updateProfessorIdByCourse(@Param("courseId") Integer courseId, @Param("professorId") Integer professorId);
}
