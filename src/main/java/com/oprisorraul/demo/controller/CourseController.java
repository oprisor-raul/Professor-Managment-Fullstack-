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
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/course")
public class CourseController {
    private final CourseRepository courseRepository;

    private final ProfessorRepository professorRepository;

    @GetMapping("{professorId}/professor")
    public ResponseEntity<List<Course>> getAllCoursesByProfessorId(@PathVariable(value = "professorId") Integer professorId) {
        if (!professorRepository.existsById(professorId)) {
//            throw new ResourceNotFoundException("Not found Professor with id = " + professorId);
            return ResponseEntity.notFound().build();
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

    @DeleteMapping("/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("courseId") Integer courseId) {
        if (courseRepository.findById(courseId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseRepository.deleteById(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable("courseId") Integer courseId, @RequestBody Course courseRequest) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Course course = courseOptional.orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));
        course.setName(courseRequest.getName());
        course.setLocalTime(courseRequest.getLocalTime());
        course.setProfessor(course.getProfessor());
        course.setDayOfWeek(course.getDayOfWeek());
        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
    }
}