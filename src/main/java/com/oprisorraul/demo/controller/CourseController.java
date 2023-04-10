package com.oprisorraul.demo.controller;

import com.oprisorraul.demo.model.Course;
import com.oprisorraul.demo.model.Professor;
import com.oprisorraul.demo.model.modelRequests.NewCourseRequest;
import com.oprisorraul.demo.repository.CourseRepository;
import com.oprisorraul.demo.repository.ProfessorRepository;
import com.oprisorraul.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("{professorId}/professor")
    public ResponseEntity<List<Course>> getAllCoursesByProfessorId(@PathVariable(value = "professorId") Integer professorId) {
        if (!professorRepository.existsById(professorId)) {
            throw new ResourceNotFoundException("Not found Professor with id = " + professorId);
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
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + professorId));

        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }




//    @PostMapping("/{courseId}/professors/{professorId}")
//    public Course addProfessorToCourse(@PathVariable Integer courseId, @PathVariable Integer professorId) {
//        // get course and professor by ID
//        Course course = courseRepository.getById(courseId);
//        Professor professor = professorRepository.getById(professorId);
//
//        // add professor to course and course to professor
//        course.setProfessor(professor);
//
//        // update course and professor
//        // return updated course
//        return course;
//    }

//    @PostMapping("/{courseId}/professors/{professorId}")
//    public ResponseEntity<Course> createCourse(@PathVariable(value = "professorID") Long tutorialId,
//                                                 @RequestBody Comment commentRequest) {
//        Comment comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
//            commentRequest.setTutorial(tutorial);
//            return commentRepository.save(commentRequest);
//        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));
//
//        return new ResponseEntity<>(comment, HttpStatus.CREATED);
//    }
}