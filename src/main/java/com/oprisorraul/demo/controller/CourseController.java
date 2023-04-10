package com.oprisorraul.demo.controller;

import com.oprisorraul.demo.model.Course;
import com.oprisorraul.demo.repository.CourseRepository;
import com.oprisorraul.demo.repository.ProfessorRepository;
import com.oprisorraul.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/course")
public class CourseController {
    private final CourseRepository courseRepository;

    private final ProfessorRepository professorRepository;

    @GetMapping("{professorId}/professor")
    public ResponseEntity<List<Course>> getAllCoursesByProfessorId(@PathVariable(value = "professorId") Integer professorId) {
        if (!professorRepository.existsById(professorId)) {
            throw new ResourceNotFoundException("Not found Professor with id = " + professorId);
        }

        List<Course> courses = courseRepository.findByProfessorId(professorId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("{professorId}/professor")
    public ResponseEntity<Course> createCourse(@PathVariable(value = "professorId") int professorId, @RequestBody Course courseRequest) {
        Course course = professorRepository.findById(professorId).map(professor -> {
            courseRequest.setProfessor(professor);
            return courseRepository.save(courseRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + professorId));

        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }
}