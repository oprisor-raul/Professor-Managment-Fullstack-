package com.oprisorraul.demo.controller;

import com.oprisorraul.demo.model.Course;
import com.oprisorraul.demo.model.Professor;
import com.oprisorraul.demo.model.modelRequests.NewCourseRequest;
import com.oprisorraul.demo.repository.CourseRepository;
import com.oprisorraul.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("{professorId}/professor")
    public ResponseEntity<List<Course>> getAllCoursesByProfessorId(@PathVariable(value = "professorId") Integer professorId) {
        if (!courseRepository.existsById(professorId)) {
            throw new com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException("Not found Professor with id = " + professorId);
        }

        List<Course> courses = courseRepository.findByProfessorId(professorId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


    @PostMapping("{professorId}/professor")
    public ResponseEntity<Course> createCourse (@PathVariable(value = "professorId") int professorId,
                                                 @RequestBody Course courseRequest) {
        Course course = professorRepository.findById(professorId).map(professor -> {
            courseRequest.setProfessor(professor);
            return courseRepository.save(courseRequest);
        }).orElseThrow(() -> new com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException("Not found Tutorial with id = " + professorId));

        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }



}
